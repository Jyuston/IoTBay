package uts.isd.controller.reporting;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.*;

import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.DaoReporting;
import uts.isd.model.reporting.TotalSales;
import uts.isd.model.reporting.TotalSalesRecord;

public class TotalSalesController implements Serializable {
    // Controller for Total Sales
    // Not Completed

    public TotalSales totalStatesWidget() {
        try {

            DBConnector connector = new DBConnector();

            Connection conn = connector.openConnection();

            DaoReporting db = new DaoReporting(conn);

            ArrayList<TotalSalesRecord> records = db.totalSales("2019-01-01 00:00:00", "2020-05-01 00:00:00");

            TotalSales totalSales = new TotalSales("Total Sales", "Some description");

            totalSales.use(records);
            
            connector.closeConnection();
            

        } 

        catch (ClassNotFoundException | SQLException ex) {

            Logger.getLogger(TotalSalesController.class.getName()).log(Level.SEVERE, null, ex);

        }

        return null;
    }
}