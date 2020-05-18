package uts.isd.model.dao;
import uts.isd.model.reporting.TotalSales;
import uts.isd.model.reporting.TotalSalesRecord;
import uts.isd.model.Customer;
import java.sql.*;
import java.util.*;

public class ReportingDAO {
    private Statement st;

    public ReportingDAO(Connection conn) throws SQLException {
        st = conn.createStatement();
    }

    public ArrayList<TotalSalesRecord> totalSales(String beginTimeStamp, String endTimeStamp) throws SQLException {
        // Set up the inital SQL query hre
        String query = ("select ORDER_LINE.PRODUCT_ID, PRODUCT_NAME, PRODUCT_CATEGORY, QUANTITY_ORDERED, ORDER_LINE.PRODUCT_PRICE, ORDER_SHIPPING_ADDRESS from ORDERS inner join ORDER_LINE on ORDERS.ORDER_ID = ORDER_LINE.ORDER_ID inner join PRODUCTS on ORDER_LINE.PRODUCT_ID = PRODUCTS.PRODUCT_ID where ORDERS.ORDER_DATE_TIME > '" + beginTimeStamp + "' AND ORDERS.ORDER_DATE_TIME < '" + endTimeStamp + "'");

        // Execute the query and store the results in the result set
        ResultSet queryResult = st.executeQuery(query);

        ArrayList<TotalSalesRecord> queryResultsStructured = new ArrayList<TotalSalesRecord>();
        
        while (queryResult.next()) {
            TotalSalesRecord record = new TotalSalesRecord(queryResult.getString(1), queryResult.getString(2), queryResult.getString(3), queryResult.getInt(4), queryResult.getDouble(5), queryResult.getString(6));            
            queryResultsStructured.add(record);
        }

        return queryResultsStructured;
    }
}