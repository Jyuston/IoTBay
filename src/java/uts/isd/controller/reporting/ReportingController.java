package uts.isd.controller.reporting;

import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.*;
import uts.isd.model.dao.*;
import uts.isd.model.reporting.Report;
import uts.isd.model.reporting.SalesAnalyser;

public class ReportingController {
    private SalesAnalyser s = new SalesAnalyser();

    public ArrayList<Report> getReports() {
        try {
            DBConnector connector = new DBConnector();
            Connection conn = connector.openConnection();
            ReportingDAO db = new ReportingDAO(conn);

            ArrayList<Report> reports = db.reports();

            connector.closeConnection();
            
            return reports;
        } 

        catch (Exception ex) {
            Logger.getLogger(ReportingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    public void createReport(String name, String description, String startDate, String endDate) {
        try {
            DBConnector connector = new DBConnector();
            Connection conn = connector.openConnection();
            ReportingDAO db = new ReportingDAO(conn);

            db.createReport(name, description, startDate, endDate);

            connector.closeConnection();
            // Note, will need to put validation in to make sure start date is before end date
        } 

        catch (Exception ex) {
            Logger.getLogger(ReportingController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Report getReport(String reportName) {

        try {
            DBConnector connector = new DBConnector();
            Connection conn = connector.openConnection();
            ReportingDAO db = new ReportingDAO(conn);

            Report r = db.getReport(reportName);

            connector.closeConnection();
            
            return r;
        } 

        catch (Exception ex) {
            Logger.getLogger(ReportingController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public String totalRevenue(String start, String end) {
        try {
            DBConnector connector = new DBConnector();
            Connection conn = connector.openConnection();
            ReportingDAO db = new ReportingDAO(conn);

            Double revenue = s.getTotalSalesValue(db.totalSales(start, end));
            DecimalFormat df1 = new DecimalFormat("####0.00");

            connector.closeConnection();
            
            return df1.format(revenue);
        } 

        catch (Exception ex) {
            Logger.getLogger(ReportingController.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }

    public String highestSellingCategory(String start, String end) {

        try {
            DBConnector connector = new DBConnector();
            Connection conn = connector.openConnection();
            ReportingDAO db = new ReportingDAO(conn);

            String category = s.getTopCategory(db.totalSales(start, end));

            connector.closeConnection();
            
            return category;
        } 

        catch (Exception ex) {
            Logger.getLogger(ReportingController.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }

    public String topCategoryRevenue(String start, String end) {
        try {
            DBConnector connector = new DBConnector();
            Connection conn = connector.openConnection();
            ReportingDAO db = new ReportingDAO(conn);

            Double categoryRevenue = s.getTopCategoryRevenue(highestSellingCategory(start, end), db.totalSales(start, end));
            DecimalFormat df1 = new DecimalFormat("####0.00");

            connector.closeConnection();
            
            return df1.format(categoryRevenue);
        } 

        catch (Exception ex) {
            Logger.getLogger(ReportingController.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }
 
}