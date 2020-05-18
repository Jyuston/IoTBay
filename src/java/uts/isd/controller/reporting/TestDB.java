/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.controller;

import java.sql.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.logging.*;
import uts.isd.model.Customer;
import uts.isd.model.dao.*;
import uts.isd.model.reporting.TotalSales;
import uts.isd.model.reporting.TotalSalesRecord;

public class TestDB {
    // This is a test controller. Used for CLI debugging
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        try {

            DBConnector connector = new DBConnector();

            Connection conn = connector.openConnection();

            DaoReporting db = new DaoReporting(conn);

            ArrayList<TotalSalesRecord> records = db.totalSales("2019-01-01 00:00:00", "2020-05-01 00:00:00");
            
            for (TotalSalesRecord totalSalesRecord : records) {
                System.out.println(totalSalesRecord.toString());
                System.out.println("State: " + totalSalesRecord.getState());
            }

            TotalSales salesObjReporter = new TotalSales("tst", "tst");

            System.out.println("The total sales value was: $" + salesObjReporter.getTotalSalesValue(records));

            System.out.println("Sales breakdown by state:");

            Hashtable<String, Double> tst = salesObjReporter.getTotalSalesByState(records);

            Set<String> dictionaryKeys = tst.keySet();
            DecimalFormat df = new DecimalFormat("####0.00");
            for (String key : dictionaryKeys) {
                System.out.println("State: " + key + "; Total Sales = $" + df.format(tst.get(key)));
            }

            connector.closeConnection();

        } 

        catch (ClassNotFoundException | SQLException ex) {

            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    
}
