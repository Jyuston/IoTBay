package uts.isd.controller.reporting;

import java.io.Serializable;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.logging.*;

import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.ReportingDAO;
import uts.isd.model.reporting.TotalSales;
import uts.isd.model.reporting.TotalSalesRecord;

public class TotalSalesController implements Serializable {
    // Controller for Total Sales
    // Not Completed

    public TotalSales totalStatesWidget() {
        try {

            Connection conn = DBConnector.getConnection();

            ReportingDAO db = new ReportingDAO(conn);

            ArrayList<TotalSalesRecord> records = db.totalSales("2019-01-01 00:00:00", "2020-05-01 00:00:00");
            

            TotalSales salesObjReporter = new TotalSales("tst", "tst");

            Hashtable<String, Double> tst = salesObjReporter.getTotalSalesByState(records);

            Set<String> dictionaryKeys = tst.keySet();
            
            DecimalFormat df = new DecimalFormat("####0.00");
        }

        catch (SQLException ex) {

            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);

        }

        return null;
    }
}