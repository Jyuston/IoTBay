package uts.isd.model.dao;

import uts.isd.model.reporting.ProductSummary;
import uts.isd.model.reporting.Report;
import uts.isd.model.reporting.OrderLineItem;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

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

    public static ArrayList<ProductSummary> productSnapshots(String beginTimeStamp, String endTimeStamp)
            throws SQLException {
        Statement st = dbConnection.createStatement();

        String query = "SELECT ol.PRODUCT_ID as id, NAME, CATEGORY as category, QUANTITY_ORDERED, (ol.PRICE * QUANTITY_ORDERED) as PRICE "
                + "FROM ORDER_LINE ol " + "INNER JOIN PRODUCTS on ol.PRODUCT_ID = PRODUCTS.ID "
                + "INNER JOIN ORDERS on ol.ORDER_ID = ORDERS.ID " + "WHERE ORDERED_ON > '" + beginTimeStamp
                + "' AND ORDERS.ORDERED_ON < '" + endTimeStamp + "'" + "ORDER BY category, id";

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
    public static Report save(String params[]) throws SQLException, ParseException {
        checkDate(params[2], params[3]);
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
    public static Report update(String reportName, String params[]) throws SQLException, DAOException, ParseException {
        checkDate(params[2], params[3]);
        //checkDuplicate(params[0]);

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
    private static void checkDate(String d1, String d2) throws ParseException {
        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(d1);
        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(d2);

        if (startDate.compareTo(endDate) > 0) {
            throw new DAOException("The start date of a report cannot be after the end date.");
        }
    }

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