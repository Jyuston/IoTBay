package uts.isd.model.dao;

import uts.isd.model.reporting.ProductSummary;
import uts.isd.model.reporting.Report;
import uts.isd.model.reporting.SalesAnalyser;
import uts.isd.model.reporting.OrderLineItem;
import uts.isd.model.Customer;
import uts.isd.model.ProductSnapshot;

import java.sql.*;
import java.util.*;

public class ReportingDAO {
    public static final Connection dbConnection = DBConnector.getConnection();

    public static ArrayList<OrderLineItem> totalSales(String beginTimeStamp, String endTimeStamp) throws SQLException {
        // Set up the inital SQL query hre
        Statement st = dbConnection.createStatement();
        /*
        String query = ("SELECT ORDER_LINE.PRODUCT_ID, NAME, CATEGORY, QUANTITY_ORDERED, ORDER_LINE.PRICE, SHIPPING_ADDRESS " +
            "FROM ORDERS inner join ORDER_LINE on ORDERS.ORDER_ID = ORDER_LINE.ORDER_ID " +
            "inner join PRODUCTS on ORDER_LINE.PRODUCT_ID = PRODUCTS.PRODUCT_ID " +
            "WHERE ORDERS.ORDERED_ON > '" + beginTimeStamp + "' AND ORDERS.ORDERED_ON < '" + endTimeStamp + "'");
        */
        String query = 
            "SELECT ORDER_LINE.PRODUCT_ID, NAME, CATEGORY, QUANTITY_ORDERED, ORDER_LINE.PRICE, SHIPPING_ADDRESS " +
            "FROM ORDERS " +
            "INNER JOIN ORDER_LINE on ORDERS.ID = ORDER_LINE.ORDER_ID " +
            "INNER JOIN PRODUCTS on ORDER_LINE.PRODUCT_ID = PRODUCTS.ID " +
            "WHERE ORDERS.ORDERED_ON > '" + beginTimeStamp + "' AND ORDERS.ORDERED_ON < '" + endTimeStamp + "'";

        // Execute the query and store the results in the result set
        ResultSet queryResult = st.executeQuery(query);

        ArrayList<OrderLineItem> queryResultsStructured = new ArrayList<OrderLineItem>();
        
        while (queryResult.next()) {
            OrderLineItem record = new OrderLineItem(queryResult.getString(1), queryResult.getString(2), queryResult.getString(3), queryResult.getInt(4), queryResult.getDouble(5), queryResult.getString(6));            
            queryResultsStructured.add(record);
        }

        return queryResultsStructured;
    }

    public static ArrayList<String> categories(String beginTimeStamp, String endTimeStamp) throws SQLException {
        // Set up the inital SQL query here
        Statement st = dbConnection.createStatement();
        String query = 
            "SELECT distinct(CATEGORY) FROM PRODUCTS P, ORDERS O " +
            "WHERE (P.ID in (SELECT P.ID FROM ORDER_LINE)) " +
            "AND (O.ORDERED_ON > '" + beginTimeStamp + "' AND O.ORDERED_ON < '" + endTimeStamp + "')";

        // Execute the query and store the results in the result set
        ResultSet queryResult = st.executeQuery(query);

        ArrayList<String> categories = new ArrayList<>();

        while (queryResult.next()) {
            categories.add(queryResult.getString(1));
        }

        return categories;
    }

    public static ArrayList<ProductSummary> productSnapshots(String beginTimeStamp, String endTimeStamp) throws SQLException {
        Statement st = dbConnection.createStatement();

        String query = 
            "SELECT ol.PRODUCT_ID as id, NAME, CATEGORY as category, QUANTITY_ORDERED, (ol.PRICE * QUANTITY_ORDERED) as PRICE " +
            "FROM ORDER_LINE ol " +
            "INNER JOIN PRODUCTS on ol.PRODUCT_ID = PRODUCTS.ID " +
            "INNER JOIN ORDERS on ol.ORDER_ID = ORDERS.ID " +
            "WHERE ORDERED_ON > '" + beginTimeStamp + "' AND ORDERS.ORDERED_ON < '" + endTimeStamp + "'" + 
            "ORDER BY category, id";
        
        // Execute the query and store the results in the result set
        ResultSet queryResult = st.executeQuery(query);

        ArrayList<ProductSummary> snapshots = new ArrayList<>();

        while (queryResult.next()) {
            ProductSummary snapshot = new ProductSummary(queryResult.getString(1), queryResult.getString(2), queryResult.getString(3), queryResult.getInt(4), queryResult.getDouble(5));
            snapshots.add(snapshot);
        }

        return snapshots;
    }

    public static ArrayList<String> getReportNames() throws SQLException {
        Statement st = dbConnection.createStatement();

        String query = "SELECT NAME FROM REPORTS";

        ResultSet queryResult = st.executeQuery(query);

        ArrayList<String> reportNames = new ArrayList<>();

        while (queryResult.next()) {
            reportNames.add(queryResult.getString(1));
        }

        return reportNames;
    }

    public static ArrayList<ProductSummary> getProductStock() throws SQLException {
        Statement st = dbConnection.createStatement();

        String query = "SELECT ID, NAME, CATEGORY, STOCK FROM PRODUCTS";

        ResultSet queryResult = st.executeQuery(query);

        ArrayList<ProductSummary> snapshots = new ArrayList<>();

        while (queryResult.next()) {
            ProductSummary snapshot = new ProductSummary(queryResult.getString(1), queryResult.getString(2), queryResult.getString(3), queryResult.getInt(4), 0.00);
            snapshots.add(snapshot);
        }

        return snapshots;
    }

    public static ArrayList<String> getProductCategories() throws SQLException {
        Statement st = dbConnection.createStatement();

        String query = "SELECT CATEGORY FROM PRODUCTS";

        ResultSet queryResult = st.executeQuery(query);

        ArrayList<String> categories = new ArrayList<>();

        while (queryResult.next()) {
            categories.add(queryResult.getString(1));
        }
        
        return categories;
    }

    // Create
    public static void save(Report r) throws SQLException {
        Statement st = dbConnection.createStatement();
        
        String reportID = generateID();

        String query = 
            "INSERT INTO REPORTS (NAME, DESCRIPTION, START_DATE, END_DATE) " +
            "VALUES " +
            "('" + r.getName() + "', '" + r.getDescription() + "', '" + 
            r.getStartDate() + " 00:00:00', '" + r.getEndDate() + " 23:59:59')";

        st.executeUpdate(query);
    }

    // Read
    public static Report get(String name) throws SQLException {
        Statement st = dbConnection.createStatement();
        
        String query = "SELECT NAME, DESCRIPTION, START_DATE, END_DATE FROM REPORTS WHERE NAME = '" + name + "'";

        ResultSet queryResult = st.executeQuery(query);

        queryResult.next();

        Report r = new Report(queryResult.getString(1), queryResult.getString(2), queryResult.getString(3), queryResult.getString(4), 
            totalSales(queryResult.getString(3), queryResult.getString(4)), 
            productSnapshots(queryResult.getString(3), queryResult.getString(4)), 
            categories(queryResult.getString(3), queryResult.getString(4)));

        return r;
    }

    // Read
    public static ArrayList<Report> getAll() throws SQLException {
        Statement st = dbConnection.createStatement();
        
        String query = "SELECT * FROM reports";
        
        // Execute the query and store the results in a result set
        ResultSet queryResult = st.executeQuery(query);

        ArrayList<Report> reports = new ArrayList<>();

        while (queryResult.next()) {
            Report r = new Report(queryResult.getString(2), queryResult.getString(3), queryResult.getString(4), queryResult.getString(5), 
            totalSales(queryResult.getString(4), queryResult.getString(5)), 
            productSnapshots(queryResult.getString(4), queryResult.getString(5)), 
            categories(queryResult.getString(4), queryResult.getString(5)));
            
            reports.add(r);
        }

        return reports;
    }

    // Update
    public static void update(Report r, String originalName) throws SQLException {
        Statement st = dbConnection.createStatement();

        String query = 
            "UPDATE REPORTS set NAME = '" + r.getName() + "', DESCRIPTION = '" + r.getDescription() + 
            "', START_DATE = '" + r.getStartDate() + " 00:00:00', END_DATE = '" + r.getEndDate() + " 23:59:59' " +
            "WHERE NAME = '" + originalName + "'";

        st.executeUpdate(query);
    }

    // Delete
    public static void delete(Report r) throws SQLException {
        Statement st = dbConnection.createStatement();

        String query = "DELETE FROM REPORTS WHERE NAME = '" + r.getName() + "'";

        st.executeUpdate(query);
    }

    // Helper Methods
    private static String generateID() throws SQLException {
        Statement st = dbConnection.createStatement();
        
        // Build the SELECT query that will retrieve all USER_IDs FROM the database
        String getIDs = "SELECT ID FROM REPORTS";

        // Execute the query against the database
        ResultSet reportIDResult = st.executeQuery(getIDs);

        // Instiate a list to store the IDs (for easy iteration)
        ArrayList<String> reportIDs = new ArrayList<String>();

        // Add the IDs to the list
        while (reportIDResult.next()) {
            reportIDs.add(reportIDResult.getString(1));
        }

        // Instantiate a new random number generator object
        Random rand = new Random();

        // Set the upper bound of the object
        int upperbound = 99999999;

        // Generate the random number
        int random = rand.nextInt(upperbound);

        // Check that the ID is in fact unique (using a helper method)
        while (!checkUnique(Integer.toString(random), reportIDs)) {
            random = rand.nextInt(upperbound);
        }

        // Return the final Report for the new record
        return "R-" + random;
    }

    private static boolean checkUnique(String value, ArrayList<String> collection) {
        // Checking to see if the supplied value already exists
        return !collection.contains(value);
    }

}