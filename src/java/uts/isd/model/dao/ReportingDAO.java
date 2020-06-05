package uts.isd.model.dao;

import uts.isd.model.reporting.ProductSummary;
import uts.isd.model.reporting.Report;
import uts.isd.model.reporting.OrderLineItem;

import java.sql.*;
import java.text.ParseException;
import java.util.*;

public class ReportingDAO {
    public static final Connection dbConnection = DBConnector.getConnection();

    public static ArrayList<OrderLineItem> totalSales(String beginTimeStamp, String endTimeStamp)
            throws SQLException, DAOException {
        // Set up the inital SQL query hre
        Statement st = dbConnection.createStatement();

        String query = "SELECT ORDER_LINE.PRODUCT_ID, NAME, CATEGORY, QUANTITY_ORDERED, ORDER_LINE.PRICE, SHIPPING_ADDRESS "
                + "FROM ORDERS " + "INNER JOIN ORDER_LINE on ORDERS.ID = ORDER_LINE.ORDER_ID "
                + "INNER JOIN PRODUCTS on ORDER_LINE.PRODUCT_ID = PRODUCTS.ID " + "WHERE ORDERS.ORDERED_ON > '"
                + beginTimeStamp + "' AND ORDERS.ORDERED_ON < '" + endTimeStamp + "'";

        // Execute the query and store the results in the result set
        ResultSet queryResult = st.executeQuery(query);

        ArrayList<OrderLineItem> queryResultsStructured = new ArrayList<OrderLineItem>();

        while (queryResult.next()) {
            OrderLineItem record = new OrderLineItem(queryResult.getString(1), queryResult.getString(2),
                queryResult.getString(3), queryResult.getInt(4), queryResult.getDouble(5),
                queryResult.getString(6));
                
            queryResultsStructured.add(record);
        }

        return queryResultsStructured;
    }

    public static ArrayList<String> categories(String beginTimeStamp, String endTimeStamp) throws SQLException {
        // Set up the inital SQL query here
        Statement st = dbConnection.createStatement();
        String query = "SELECT distinct(CATEGORY) FROM PRODUCTS P, ORDERS O "
                + "WHERE (P.ID in (SELECT PRODUCT_ID FROM ORDER_LINE)) " 
                + "AND (O.ORDERED_ON > '" + beginTimeStamp
                + "' AND O.ORDERED_ON < '" + endTimeStamp + "')";

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
        
        String query = "SELECT p.id, p.\"NAME\", p.CATEGORY, SUM(ol.QUANTITY_ORDERED) AS TotalQuantity, SUM(ol.QUANTITY_ORDERED * ol.PRICE) AS TotalPrice " +
                "FROM ORDER_LINE ol " +
                "INNER JOIN PRODUCTS p ON ol.PRODUCT_ID = p.ID " +
                "INNER JOIN ORDERS o ON ol.ORDER_ID = o.ID " + 
                "WHERE o.ORDERED_ON > '" + beginTimeStamp + "' AND o.ORDERED_ON < '" + endTimeStamp + "' " +
                "GROUP BY p.ID, p.\"NAME\", p.CATEGORY";

        // Execute the query and store the results in the result set
        ResultSet queryResult = st.executeQuery(query);

        ArrayList<ProductSummary> snapshots = new ArrayList<>();

        while (queryResult.next()) {
            ProductSummary snapshot = new ProductSummary(queryResult.getString(1), queryResult.getString(2),
                queryResult.getString(3), queryResult.getInt(4), queryResult.getDouble(5));
            
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

        // Retrievs a product and its stock if its not archived
        String query = "SELECT ID, NAME, CATEGORY, STOCK " +
            "FROM PRODUCTS " +
            "WHERE CATEGORY IN (SELECT CATEGORY FROM PRODUCT_CATEGORIES WHERE NOT ARCHIVED)" ;

        ResultSet queryResult = st.executeQuery(query);

        ArrayList<ProductSummary> snapshots = new ArrayList<>();

        while (queryResult.next()) {
            ProductSummary snapshot = new ProductSummary(queryResult.getString(1), queryResult.getString(2),
                    queryResult.getString(3), queryResult.getInt(4), 0.00);
            snapshots.add(snapshot);
        }

        return snapshots;
    }

    public static ArrayList<String> getProductCategories() throws SQLException {
        Statement st = dbConnection.createStatement();

        // Gets a category if it has not been archived
        String query = "SELECT CATEGORY " + 
            "FROM PRODUCTS " + 
            "WHERE CATEGORY IN (SELECT CATEGORY FROM PRODUCT_CATEGORIES WHERE NOT ARCHIVED)";

        ResultSet queryResult = st.executeQuery(query);

        ArrayList<String> categories = new ArrayList<>();

        while (queryResult.next()) {
            categories.add(queryResult.getString(1));
        }

        return categories;
    }

    // Create
    public static Report save(String[] params) throws SQLException, ParseException {
        checkDuplicate(params[0]);

        Statement st = dbConnection.createStatement();

        String query = 
            "INSERT INTO REPORTS (NAME, DESCRIPTION, START_DATE, END_DATE) " +
            "VALUES " +
            "('" + params[0] + "', '" + params[1] + "', '" + 
            params[2] + " 00:00:00', '" + params[3] + " 23:59:59')";

        int rowsChanged = st.executeUpdate(query);

        checkExecutionFails(rowsChanged);

        return get(params[0]);
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
    public static Report update(String reportName, String[] params, Boolean nameChanged) throws SQLException, DAOException, ParseException {
        // If the name of the report was changed, a check must be done to ensure the name is unique
        if (nameChanged) {
            checkDuplicate(params[0]);
        }

        Statement st = dbConnection.createStatement();

        String query = 
            "UPDATE REPORTS set NAME = '" + params[0] + "', DESCRIPTION = '" + params[1] + 
            "', START_DATE = '" + params[2] + " 00:00:00', END_DATE = '" + params[3] + " 23:59:59' " +
            "WHERE NAME = '" + reportName + "'";

        int rowsChanged = st.executeUpdate(query);

        checkExecutionFails(rowsChanged);

        return get(params[0]);
    }

    // Delete
    public static void delete(Report r) throws SQLException, DAOException {
        Statement st = dbConnection.createStatement();

        String query = "DELETE FROM REPORTS WHERE NAME = '" + r.getName() + "'";

        int rowsChanged = st.executeUpdate(query);

        checkExecutionFails(rowsChanged);        
    }
    
    // Helper Functions

    private static void checkDuplicate(String name) throws SQLException {
        ArrayList<String> names = getReportNames();
        
        for (String string : names) {
            if (string.equals(name)) {
                throw new DAOException("The report name, '" + name + "', is already in use. Please choose another report name.");
            }
        }
    }

    private static void checkExecutionFails(int i) {
        if (i == 0) {
            throw new DAOException("Execution failed in DAO. No rows were changed.");
        }
    }
}