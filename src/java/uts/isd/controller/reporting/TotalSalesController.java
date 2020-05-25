package uts.isd.controller.reporting;

import java.io.Serializable;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.logging.*;

import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.ReportingDAO;
import uts.isd.model.reporting.SalesAnalyser;
import uts.isd.model.reporting.OrderLineItem;

public class TotalSalesController implements Serializable {
    // Controller for Total Sales
    // Not Completed

    public SalesAnalyser totalStatesWidget() {
        try {

            DBConnector connector = new DBConnector();

            Connection conn = connector.openConnection();

            ReportingDAO db = new ReportingDAO(conn);

            ArrayList<OrderLineItem> records = db.totalSales("2019-01-01 00:00:00", "2020-05-01 00:00:00");
            

            SalesAnalyser salesObjReporter = new SalesAnalyser();

            Hashtable<String, Double> tst = salesObjReporter.getSalesBySate(records);

            Set<String> dictionaryKeys = tst.keySet();
            
            DecimalFormat df = new DecimalFormat("####0.00");

            connector.closeConnection();

        } 

        catch (ClassNotFoundException | SQLException ex) {

            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);

        }

        return null;
    }
}