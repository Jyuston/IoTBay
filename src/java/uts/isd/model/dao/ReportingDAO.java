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
    private Statement st;

    public ReportingDAO(Connection conn) throws SQLException {
        st = conn.createStatement();
    }

    public ArrayList<OrderLineItem> totalSales(String beginTimeStamp, String endTimeStamp) throws SQLException {
        // Set up the inital SQL query hre
        String query = ("select ORDER_LINE.PRODUCT_ID, PRODUCT_NAME, PRODUCT_CATEGORY, QUANTITY_ORDERED, ORDER_LINE.PRODUCT_PRICE, ORDER_SHIPPING_ADDRESS " +
            "from ORDERS inner join ORDER_LINE on ORDERS.ORDER_ID = ORDER_LINE.ORDER_ID " +
            "inner join PRODUCTS on ORDER_LINE.PRODUCT_ID = PRODUCTS.PRODUCT_ID " +
            "where ORDERS.ORDER_DATE_TIME > '" + beginTimeStamp + "' AND ORDERS.ORDER_DATE_TIME < '" + endTimeStamp + "'");

        // Execute the query and store the results in the result set
        ResultSet queryResult = st.executeQuery(query);

        ArrayList<OrderLineItem> queryResultsStructured = new ArrayList<OrderLineItem>();
        
        while (queryResult.next()) {
            OrderLineItem record = new OrderLineItem(queryResult.getString(1), queryResult.getString(2), queryResult.getString(3), queryResult.getInt(4), queryResult.getDouble(5), queryResult.getString(6));            
            queryResultsStructured.add(record);
        }

        return queryResultsStructured;
    }

    public ArrayList<String> categories(String beginTimeStamp, String endTimeStamp) throws SQLException {
        // Set up the inital SQL query hre
        String query = ("select distinct(PRODUCT_CATEGORY) from PRODUCTS, ORDERS O " +
            "where (PRODUCT_ID in (select PRODUCT_ID from ORDER_LINE)) " +
            "AND (O.ORDER_DATE_TIME > '" + beginTimeStamp + "' AND O.ORDER_DATE_TIME < '" + endTimeStamp + "')");

        // Execute the query and store the results in the result set
        ResultSet queryResult = st.executeQuery(query);

        ArrayList<String> categories = new ArrayList<>();

        while (queryResult.next()) {
            categories.add(queryResult.getString(1));
        }

        return categories;
    }

    public ArrayList<ProductSummary> productSnapshots(String beginTimeStamp, String endTimeStamp) throws SQLException {
        String query = ("select o.PRODUCT_ID as id, PRODUCT_NAME, PRODUCT_CATEGORY as category, QUANTITY_ORDERED, (o.PRODUCT_PRICE * QUANTITY_ORDERED) as PRICE " +
            "from ORDER_LINE o inner join PRODUCTS on o.PRODUCT_ID = products.PRODUCT_ID " +
            "inner join ORDERS on o.ORDER_ID = ORDERS.ORDER_ID " +
            "where ORDER_DATE_TIME > '" + beginTimeStamp + "' AND ORDERS.ORDER_DATE_TIME < '" + endTimeStamp + "'" + 
            "order by category, id");
        
        // Execute the query and store the results in the result set
        ResultSet queryResult = st.executeQuery(query);

        ArrayList<ProductSummary> snapshots = new ArrayList<>();

        while (queryResult.next()) {
            ProductSummary snapshot = new ProductSummary(queryResult.getString(1), queryResult.getString(2), queryResult.getString(3), queryResult.getInt(4), queryResult.getDouble(5));
            snapshots.add(snapshot);
        }

        return snapshots;
    }

    public ArrayList<Report> reports() throws SQLException {
        String query = "select * from reports";
        
        // Execute the query and store the results in a result set
        ResultSet queryResult = st.executeQuery(query);

        ArrayList<Report> reports = new ArrayList<>();

        while (queryResult.next()) {
            Report report = new Report(queryResult.getString(2), queryResult.getString(3), queryResult.getString(4), queryResult.getString(5));
            reports.add(report);
        }

        return reports;
    }

    public ArrayList<String> getReportNames() throws SQLException {
        String query = "select REPORT_NAME from REPORTS";

        ResultSet queryResult = st.executeQuery(query);

        ArrayList<String> reportNames = new ArrayList<>();

        while (queryResult.next()) {
            reportNames.add(queryResult.getString(1));
        }

        return reportNames;
    }

    public void createReport(String name, String description, String startDate, String endDate) throws SQLException {
        String reportID = generateID();

        String query = ("insert into REPORTS (REPORT_ID, REPORT_NAME, REPORT_DESCRIPTION, REPORT_START_DATE, REPORT_END_DATE) values " +
            "('" + reportID + "', '" + name + "', '" + description + "', '" + startDate + " 00:00:00', '" + endDate + " 23:59:59')");

        st.executeUpdate(query);
    }

    private String generateID() throws SQLException {
        // Build the select query that will retrieve all USER_IDs from the database
        String getIDs = "select REPORT_ID from REPORTS";

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

    private boolean checkUnique(String value, ArrayList<String> collection) {
        // Checking to see if the supplied value already exists
        return !collection.contains(value);
    }

    public Report getReport(String name) throws SQLException {
        String query = "select REPORT_NAME, REPORT_DESCRIPTION, REPORT_START_DATE, REPORT_END_DATE from REPORTS where REPORT_NAME = '" + name + "'";

        ResultSet queryResult = st.executeQuery(query);

        queryResult.next();

        Report r = new Report(queryResult.getString(1), queryResult.getString(2), queryResult.getString(3), queryResult.getString(4));
        return r;
    }
}