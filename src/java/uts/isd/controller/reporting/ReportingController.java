package uts.isd.controller.reporting;

import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.logging.*;
import uts.isd.model.dao.*;
import uts.isd.model.reporting.ProductSummary;
import uts.isd.model.reporting.Report;
import uts.isd.model.reporting.SalesAnalyser;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ReportingController extends HttpServlet {
    private static final ReportingDAO DAO = new ReportingDAO();
    
    public ArrayList<Report> getReports() throws SQLException {
        return DAO.getAll();
    }

    /*
    public Report createReport(String name, String description, String startDate, String endDate) throws SQLException {
        Report r = new Report(name, description, startDate, endDate, DAO.totalSales(startDate, endDate));
        DAO.save(r);
        return r;
    }

    public Report getReport(String reportName) throws SQLException {
        return DAO.get(reportName);
    }

    public String totalRevenue(Report r) {
        DecimalFormat formatter = new DecimalFormat("####0.00");
        
        Double revenue = r.getTotalRevenue();
        return formatter.format(revenue);
    }

    public String topCategory(Report r) {
        return r.getTopCategory();
    }

    public String topCategoryRevenue(Report r) {
        DecimalFormat formatter = new DecimalFormat("####0.00");

        Double revenue =  r.getTopCategoryRevenue();

        return formatter.format(revenue);
    }

    public ProductSummary topSellingItem(Report r) throws SQLException {
        ArrayList<ProductSummary> summaries = DAO.productSnapshots(r.getStartDate(), r.getEndDate());
        return r.getTopSellingItem(summaries);
    }

    public String topSellingItemName(Report r) throws SQLException {
        return topSellingItem(r).getProductName();
    }

    public String topSellingItemQuantity(Report r) throws SQLException {
        return Integer.toString(topSellingItem(r).getUnitsSold());
    }

    public String topSellingItemRevenue(Report r) throws SQLException {
        DecimalFormat formatter = new DecimalFormat("####0.00");

        return formatter.format(topSellingItem(r).getProductRevenue());
    }

    public String topSellingProductID(Report r) throws SQLException {
        return topSellingItem(r).getProductID();
    }

    public Hashtable<String, Double> salesByState(Report r) throws SQLException {
        return r.getSalesByState();
    }

    public Hashtable<String, Double> salesByCategory(Report r) throws SQLException {
        return r.getSalesbyCategory();
    }

    public HashMap<String, ArrayList<ProductSummary>> salesByCategorybyProduct(Report r) throws SQLException {
        return r.getSalesByCategoryByProduct(DAO.productSnapshots(r.getStartDate(), r.getEndDate()), DAO.categories(r.getStartDate(), r.getEndDate()));
    }
    */
}