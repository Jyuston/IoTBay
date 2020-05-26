package uts.isd.controller.reporting;

import java.sql.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.logging.*;
import uts.isd.model.dao.*;
import uts.isd.model.reporting.OrderLineSnapshot;
import uts.isd.model.reporting.TotalSales;
import uts.isd.model.reporting.TotalSalesRecord;

public class TestDB {
    // This is a test controller. Used for CLI debugging
    private static Scanner in = new Scanner(System.in);

//    public static void main(String[] args) {
//        try {
//            Connection conn = DBConnector.getConnection();
//
//            ReportingDAO db = new ReportingDAO(conn);
//
//            // DEBUG - Checking that sale records are being retrieved from the db
//
//            ArrayList<TotalSalesRecord> records = db.totalSales("2019-01-01 00:00:00", "2020-05-01 00:00:00");
//
//            System.out.println("============ Printing out order information + order_line information ============");
//            for (TotalSalesRecord totalSalesRecord : records) {
//                System.out.println(totalSalesRecord.toString());
//                System.out.println("State: " + totalSalesRecord.getState());
//            }
//
//            // DEBUG - Checking Total Revenue calculation function
//
//            TotalSales salesObjReporter = new TotalSales("tst", "tst");
//            System.out.println("============ Printing out total revenue for the period ============");
//            System.out.println("The total sales value was: $" + salesObjReporter.getTotalSalesValue(records));
//
//            // DEBUG - Checking Sales Breakdown by State functionality
//            System.out.println("============ Printing out sales by state ============");
//
//            Hashtable<String, Double> tst = salesObjReporter.getTotalSalesByState(records);
//
//            Set<String> dictionaryKeys = tst.keySet();
//            DecimalFormat df = new DecimalFormat("####0.00");
//            for (String key : dictionaryKeys) {
//                System.out.println("State: " + key + "; Total Sales = $" + df.format(tst.get(key)));
//            }
//
//            // DEBUG - Checking Sales Summary by Category functionality
//            System.out.println("============ Printing out sales by category summary ============");
//            Hashtable<String, Double> tst1 = salesObjReporter.getTotalSalesByProductCategorySummary(records);
//
//            Set<String> dictionaryKeys1 = tst1.keySet();
//            DecimalFormat df1 = new DecimalFormat("####0.00");
//            for (String key : dictionaryKeys1) {
//                System.out.println("Product Category: " + key + "; Total Sales = $" + df1.format(tst1.get(key)));
//            }
//
//            // DEBUG - Checking Sales by Category by Product functionality
//            System.out.println("============ Printing out sales by category by product ============");
//            HashMap<String, ArrayList<OrderLineSnapshot>> tst2 = salesObjReporter.getTotalSalesByProductCategory(db.productSnapshots("2019-01-01 00:00:00", "2020-05-01 00:00:00"), db.categories("2019-01-01 00:00:00", "2020-05-01 00:00:00"));
//            //Print out the values
//            Set<String> dictionaryKeys2 = tst2.keySet();
//            for (String string : dictionaryKeys2) {
//                ArrayList<OrderLineSnapshot> list = tst2.get(string);
//
//                for (OrderLineSnapshot s : list) {
//                    s.printObj();
//                }
//            }
//        }
//
//        catch (SQLException ex) {
//
//            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
//
//        }
//    }
    
}
