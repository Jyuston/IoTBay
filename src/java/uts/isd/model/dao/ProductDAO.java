package uts.isd.model.dao;

import uts.isd.model.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class ProductDAO {
    /**
     * Find a single Product by Product ID.
     *
     * @return An instantiated Product if found, otherwise null.
     */
    public static Product get(int productID) throws SQLException, DAOException {
        String getProductDataQuery =
                "SELECT * FROM PRODUCTS " +
                "WHERE ID = " + productID;

        PreparedStatement st = DAOUtils.prepareStatement(getProductDataQuery, false);
        ResultSet productRs = st.executeQuery();

        // If no data, return null Product
        if (!productRs.next())
            throw new DAOException("No Product with that ID exists.");

        return createProductObject(productRs);
    }

    /**
     * Find all products within the database.
     *
     * @return A list of all products within the database
     */
    public static LinkedList<Product> getAll() throws SQLException {
        LinkedList<Product> products = new LinkedList<>();

        String getProductsQuery = "SELECT * FROM PRODUCTS ";
        PreparedStatement st = DAOUtils.prepareStatement(getProductsQuery, false);
        ResultSet productsRs = st.executeQuery();

        while (productsRs.next())
            products.add(createProductObject(productsRs));

        return products;
    }

    /**
     * Search for a list of products by name.
     *
     * @param query The product name to search
     * @return A list of products with names matching the search
     */
    public static LinkedList<Product> searchByName(String query) throws SQLException {
        LinkedList<Product> products = new LinkedList<>();

        String searchQuery = "SELECT * FROM PRODUCTS WHERE NAME LIKE ?";
        PreparedStatement st = DAOUtils.prepareStatement(searchQuery, false,
                "%" + query + "%"
        );

        ResultSet productsRs = st.executeQuery();

        while (productsRs.next()) {
            products.add(createProductObject(productsRs));
        }

        return products;
    }

    /**
     * Search for a list of products by the category they belong to.
     *
     * @param query The product category to search
     * @return A list of products that belong to a category
     */
    public static LinkedList<Product> searchByCategory(String query) throws SQLException {
        LinkedList<Product> products = new LinkedList<>();

        String searchQuery = "SELECT * FROM PRODUCTS WHERE CATEGORY LIKE ?";
        PreparedStatement st = DAOUtils.prepareStatement(searchQuery, false,
                "%" + query + "%"
        );

        ResultSet productsRs = st.executeQuery();

        while (productsRs.next()) {
            products.add(createProductObject(productsRs));
        }

        return products;
    }

    /**
     * Search for products by name and optionally category as well.
     *
     * @param name The product name to search
     * @param category The product name to search
     * @return A list of products that belong to a category
     */
    public static LinkedList<Product> search(String name, String category) throws SQLException {
        LinkedList<Product> products = new LinkedList<>();

        String searchQuery =
                "SELECT * FROM PRODUCTS " +
                "WHERE NAME LIKE ? " +
                "AND CATEGORY LIKE ?";

        PreparedStatement st = DAOUtils.prepareStatement(searchQuery, false,
                "%" + name + "%",
                "%" + category + "%"
        );

        ResultSet productsRs = st.executeQuery();

        while (productsRs.next()) {
            products.add(createProductObject(productsRs));
        }

        return products;
    }

    /**
     * Save a new product to the database.
     * New products can be created by instantiating a new model instance.
     *
     * @param product Product to save to DB
     */
    public static void save(Product product) throws SQLException, DAOException {
        String productInsertQuery =
                "INSERT INTO PRODUCTS (CATEGORY, NAME, DESCRIPTION, STOCK, PRICE, ARCHIVED) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement productInsertSt = DAOUtils.prepareStatement(productInsertQuery, true,
                product.getCategory(),
                product.getName(),
                product.getDescription(),
                product.getStock(),
                product.getPrice(),
                product.isArchived()
        );

        int rowsChanged = productInsertSt.executeUpdate();
        if (rowsChanged == 0)
            throw new DAOException("Failed to add new product. Please try again.");

        // Get the generated ID of the new product
        int generatedID = DAOUtils.getGeneratedID(productInsertSt);

        // Update instances ID and return true for successful write
        product.setID(generatedID);
    }

    /**
     * Update a single product from the database.
     *
     * @param product The instantiated product to update. Must have an ID.
     */
    public static void update(Product product) throws SQLException, DAOException {
        String updateQuery =
                "UPDATE PRODUCTS SET NAME = ?,STOCK = ?, PRICE = ?, CATEGORY = ?,DESCRIPTION = ?, ARCHIVED = ? " +
                "WHERE ID = ?";

        PreparedStatement updateSt = DAOUtils.prepareStatement(updateQuery, false,
                product.getName(),
                product.getStock(),
                product.getPrice(),
                product.getCategory(),
                product.getDescription(),
                product.isArchived(),
                product.getID()
        );

        int rowsChanged = updateSt.executeUpdate();
        if (rowsChanged == 0)
            throw new DAOException("Failed to update product. Please try again.");
    }

    /**
     * Delete a single product from the database.
     *
     * @param productID ID of the product to delete
     */
    public void delete(String productID) throws SQLException, DAOException {
        String deleteQuery = "DELETE FROM PRODUCTS WHERE ID = " + productID;

        PreparedStatement st = DAOUtils.prepareStatement(deleteQuery, false);

        int rowsChanged = st.executeUpdate();
        if (rowsChanged == 0)
            throw new DAOException("Failed to delete Product. Please try again.");
    }

    // Helpers

    /**
     * Used to create Product instances from SQL ResultSets after executing a db query.
     *
     * Will create the Product instance based on the current cursor position of the ResultSet.
     * This means this helper method can be used within loops.
     *
     * @param productRs the ResultSet of the Product(s)
     * @return An instantiated Product object
     */
    private static Product createProductObject(ResultSet productRs) throws SQLException {
        Product newProduct = new Product();
        newProduct.setID(productRs.getInt("ID"));
        newProduct.setName(productRs.getString("NAME"));
        newProduct.setCategory(productRs.getString("CATEGORY"));
        newProduct.setDescription(productRs.getString("DESCRIPTION"));
        newProduct.setStock(productRs.getInt("STOCK"));
        newProduct.setPrice(productRs.getDouble("PRICE"));
        newProduct.setArchived(productRs.getBoolean("ARCHIVED"));

        return newProduct;
    }
}
