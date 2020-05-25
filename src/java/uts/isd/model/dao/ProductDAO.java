package uts.isd.model.dao;

import uts.isd.model.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class ProductDAO implements DAO<Product> {
    private static final Connection dbConnection = DBConnector.getConnection();

        public static String getNextAvailableID() throws SQLException {
        Statement st = dbConnection.createStatement();

        String productIDs = "SELECT PRODUCT_ID FROM PRODUCTS";
        ResultSet productIDsRs = st.executeQuery(productIDs);

        if (!productIDsRs.last())
            return "P-1";

        String lastID = productIDsRs.getString("USER_ID");
        int lastNumber = Integer.parseInt(lastID.substring(2));
        return "P-" + (lastNumber + 1);
    }
    
    
    
    public Product get(String productName, String productCategory) throws SQLException {
        // Setting up the initial SQL query to find the product by its name and type (category)
        Statement st = dbConnection.createStatement();
        String getProductID =
                "SELECT PRODUCT_ID FROM PRODUCTS " +
                "WHERE PRODUCT_NAME LIKE '" + productName + "' " +
                "AND PRODUCT_CATEGORY LIKE '" + productCategory + "'";

        ResultSet productIDRs = st.executeQuery(getProductID);

        // If no user, return null
        if (!productIDRs.next())
            return null;

        return get(productIDRs.getString("PRODUCT_ID"));
    }

    @Override
    public Product get(String productID) throws SQLException {
        Statement st = dbConnection.createStatement();
        String getProductData =
                "SELECT * FROM PRODUCTS " +
                "WHERE PRODUCT_ID = '" + productID + "'";

        ResultSet productRs = st.executeQuery(getProductData);

        // If no data, return null Customer
        if (!productRs.next())
            return null;

        return createProductObject(productRs);
    }

    @Override
    public List<Product> getAll() throws SQLException {
        LinkedList<Product> products = new LinkedList<>();

        Statement st = dbConnection.createStatement();
        String getProductInfo =
                "SELECT * FROM PRODUCTS ";

        ResultSet productARs = st.executeQuery(getProductInfo);

        while (productARs.next()) {
            products.add(createProductObject(productARs));
        }

        return products;
    }

    @Override
    public void save(Product product) throws SQLException {
        Statement st = dbConnection.createStatement();

        String productInsertQuery = String.format(
                "INSERT INTO PRODUCTS (PRODUCT_ID, PRODUCT_NAME, STOCK, PRODUCT_PRICE, PRODUCT_CATEGORY, PRODUCT_DESCRIPTION, ARCHIVED) " +
                "VALUES ('%s', '%s', '%s', '%s', '%s', '%s')",
                product.getID(),
                product.getName(),
                product.getStock(),
                product.getPrice(),
                product.getCategory(),
                product.isArchived()
        );
        st.executeQuery(productInsertQuery);

      
    }

    @Override
    public void update(Product product, String[] params) {
    }

    @Override
    public void delete(Product product) throws SQLException {
    }

    // Helpers
    private Product createProductObject(ResultSet productRs) throws SQLException {
        return new Product(
                productRs.getString("PRODUCT_NAME"),
                productRs.getInt("STOCK"),
                productRs.getDouble("PRODUCT_PRICE"),
                productRs.getString("PRODUCT_CATEGORY"), 
                productRs.getBoolean("ARCHIVED")
                
        );
    }
}
