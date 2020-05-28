package uts.isd.model.dao;

import uts.isd.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class ProductDAO{
    
    public static final Connection dbConnection = DBConnector.getConnection();
        
// get the last product and add one to it     
        public static String getNextAvailableID() throws SQLException {
        Statement st = dbConnection.createStatement();

        String productIDsQuery = "SELECT PRODUCT_ID FROM PRODUCTS";
        ResultSet productIDsRs = st.executeQuery(productIDsQuery);

        // BROKEN RN
        if (!productIDsRs.next())
            return "U-1";

        String lastID = productIDsRs.getString("PRODUCT_ID");
        int lastNumber = Integer.parseInt(lastID.substring(2));
        return "P-" + (lastNumber + 1);
    }
    

//reads ID and enables to perform the update and delete functions in view
    public static Product get(String productID) throws SQLException {
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
   
    // only for the read function in where a user can search a product by typing name and cat
    public static Product getRead(String productName, String productCategory) throws SQLException {
// Setting up the initial SQL query to find the product by its name and type (category)
        Statement st = dbConnection.createStatement();
        String getProductID =
                "SELECT PRODUCT_ID FROM PRODUCTS " +
                "WHERE PRODUCT_NAME LIKE '" + productName + "' " +
                "AND PRODUCT_CATEGORY LIKE '" + productCategory + "'";

        ResultSet productIDRs = st.executeQuery(getProductID);

        // If no user, return null
        
        while (productIDRs.next()) {
            String pName = productIDRs.getString(1);
            String pCat = productIDRs.getString(4);
            if (pName.equals(productName) && pCat.equals(productCategory)) {
                String pID = productIDRs.getString(0);
                int stock = productIDRs.getInt(2);
                double pPrice = productIDRs.getDouble(3);
                String pDesc = productIDRs.getString(5);
                boolean arch = productIDRs.getBoolean(6);
                return new Product(pID, pName, stock, pPrice, pCat, pDesc, arch);
                
            }
            
        }
        return null; 
        
    }    


  
     
    //gets all the products to show all the product catalogue in the view 
    public static List<Product> getAll() throws SQLException {
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
    
    
  
    public static void save(Product product) throws SQLException {
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

    
   
    
    public static boolean update(Product product, String[] params) throws SQLException {
         String updatePInfo = "UPDATE PRODUCTS SET PRODUCT_NAME = ?,STOCK = ?,  "
                + "PRODUCT_PRICE = ?, PRODUCT_CATEGORY = ?,PRODUCT_DESCRIPTION = ?, ARCHIVED = ? "
                + "WHERE PRODUCT_ID = ?";
        

        boolean isUpdated = false;
        
        try (PreparedStatement statement = ProductDAO.dbConnection.prepareStatement(updatePInfo)) {
            statement.setString(1, product.getName());
            statement.setInt(2, product.getStock());
            statement.setDouble(3,  product.getPrice());
            statement.setString(4, product.getCategory());
            statement.setString(5, product.getDescription());
            statement.setBoolean(6, product.isArchived());
            
            isUpdated = statement.executeUpdate() > 0;

            statement.close();
        }
        return isUpdated;
    }

    public boolean deleteProduct(String productID) throws SQLException {
        
        String dProd = "DELETE FROM PRODUCTS WHERE PRODUCT_ID = ?";

        boolean isDeleted = false;
        try (PreparedStatement statement = this.dbConnection.prepareStatement(dProd)) {
            
            statement.setString(0, productID);
            
            isDeleted = statement.executeUpdate() > 0;

            statement.close();
        }
        return isDeleted;
    }

    // Helpers
    private static Product createProductObject(ResultSet productRs) throws SQLException {
        return new Product(
                productRs.getString("PRODUCT_ID"),
                productRs.getString("PRODUCT_NAME"),
                productRs.getInt("STOCK"),
                productRs.getDouble("PRODUCT_PRICE"),
                productRs.getString("PRODUCT_CATEGORY"),
                productRs.getString("PRODUCT_DESCRIPTION"),                 
                productRs.getBoolean("ARCHIVED")
                
        );
    }

   
}
