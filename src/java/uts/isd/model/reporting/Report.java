package uts.isd.model.reporting;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

import uts.isd.model.dao.ReportingDAO;

public class Report implements Serializable {
    private String name;
    private String description;
    private String startDate;
    private String endDate;
    private ArrayList<OrderLineItem> saleRecords;
    private ArrayList<ProductSummary> productSummaries;
    private ArrayList<String> categories;
    private SalesAnalyser salesAnalyser = new SalesAnalyser();

    /*
    private double totalRevenue;
    private String topCategory;
    private double topCategoryRevenue;
    private ProductSummary topItem;
    */
    

    public Report (String reportName, String reportDescription, String startDate, String endDate, 
        ArrayList<OrderLineItem> saleRecords, ArrayList<ProductSummary> summaries, ArrayList<String> categories) {
        this.name = reportName;
        this.description = reportDescription;
        this.startDate = startDate;
        this.endDate = endDate;
        this.saleRecords = saleRecords;
        this.productSummaries = summaries;
        this.categories = categories;
        /*
        this.totalRevenue = salesAnalyser.getTotalSalesValue(saleRecords);
        this.topCategory = salesAnalyser.getTopCategory(saleRecords);
        this.topCategoryRevenue = salesAnalyser.getTopCategoryRevenue(topCategory, saleRecords);
        this.topItem = salesAnalyser.getTopProduct(summaries);
        */
    }

    public String getName() {
        return name;
    }

    public void setReportName(String reportName) {
        this.name = reportName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String reportDescription) {
        this.description = reportDescription;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setReportStartDate(String reportStartDate) {
        this.startDate = reportStartDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setReportEndDate(String reportEndDate) {
        this.endDate = reportEndDate;
    }

    public void printReportInfo() {
        System.out.println("Report Name: " + name);
        System.out.println("Report Description: " + description);
        System.out.println("Report Start Date: " + startDate);
        System.out.println("Report End Date: " + endDate);
        System.out.println("-----------");
    }

    public Double getTotalRevenue() {
        return salesAnalyser.getTotalSalesValue(saleRecords);
    }

    public String getTopCategory() {
        return salesAnalyser.getTopCategory(saleRecords);
    }

    public Double getTopCategoryRevenue() {
        return salesAnalyser.getTopCategoryRevenue(getTopCategory(), saleRecords);
    }

    public ProductSummary getTopSellingItem() {
        return salesAnalyser.getTopProduct(productSummaries);
    }

    public Hashtable<String, Double> getSalesByState() {
        return salesAnalyser.getSalesBySate(saleRecords);
    }

    public Hashtable<String, Double> getSalesByCategory() {
        return salesAnalyser.getSalesByCategory(saleRecords);
    }

    public HashMap<String, ArrayList<ProductSummary>> getSalesBreakdown() {
        return salesAnalyser.getSalesByCategoryByProduct(productSummaries, categories);
    }

}