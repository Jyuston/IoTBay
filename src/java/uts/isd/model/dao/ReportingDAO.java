package uts.isd.model.dao;
import uts.isd.model.reporting.OrderLineSnapshot;
import uts.isd.model.reporting.TotalSales;
import uts.isd.model.reporting.TotalSalesRecord;
import uts.isd.model.Customer;
import uts.isd.model.ProductSnapshot;

import java.sql.*;
import java.util.*;

public class ReportingDAO {
    private Statement st;

    public ReportingDAO(Connection conn) throws SQLException {
        st = conn.createStatement();
    }

    public ArrayList<TotalSalesRecord> totalSales(String beginTimeStamp, String endTimeStamp) throws SQLException {
        // Set up the inital SQL query hre
        String query = ("select ORDER_LINE.PRODUCT_ID, PRODUCT_NAME, PRODUCT_CATEGORY, QUANTITY_ORDERED, ORDER_LINE.PRODUCT_PRICE, ORDER_SHIPPING_ADDRESS " +
            "from ORDERS inner join ORDER_LINE on ORDERS.ORDER_ID = ORDER_LINE.ORDER_ID " +
            "inner join PRODUCTS on ORDER_LINE.PRODUCT_ID = PRODUCTS.PRODUCT_ID " +
            "where ORDERS.ORDER_DATE_TIME > '" + beginTimeStamp + "' AND ORDERS.ORDER_DATE_TIME < '" + endTimeStamp + "'");

        // Execute the query and store the results in the result set
        ResultSet queryResult = st.executeQuery(query);

        ArrayList<TotalSalesRecord> queryResultsStructured = new ArrayList<TotalSalesRecord>();
        
        while (queryResult.next()) {
            TotalSalesRecord record = new TotalSalesRecord(queryResult.getString(1), queryResult.getString(2), queryResult.getString(3), queryResult.getInt(4), queryResult.getDouble(5), queryResult.getString(6));            
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

    public ArrayList<OrderLineSnapshot> productSnapshots(String beginTimeStamp, String endTimeStamp) throws SQLException {
        String query = ("select o.PRODUCT_ID as id, PRODUCT_NAME, PRODUCT_CATEGORY as category, QUANTITY_ORDERED, (o.PRODUCT_PRICE * QUANTITY_ORDERED) as PRICE " +
            "from ORDER_LINE o inner join PRODUCTS on o.PRODUCT_ID = products.PRODUCT_ID " +
            "inner join ORDERS on o.ORDER_ID = ORDERS.ORDER_ID " +
            "where ORDER_DATE_TIME > '" + beginTimeStamp + "' AND ORDERS.ORDER_DATE_TIME < '" + endTimeStamp + "'");
        
        // Execute the query and store the results in the result set
        ResultSet queryResult = st.executeQuery(query);

        ArrayList<OrderLineSnapshot> snapshots = new ArrayList<>();

        while (queryResult.next()) {
            OrderLineSnapshot snapshot = new OrderLineSnapshot(queryResult.getString(1), queryResult.getString(2), queryResult.getString(3), queryResult.getInt(4), queryResult.getDouble(5));
            snapshots.add(snapshot);
        }

        return snapshots;
    }
}