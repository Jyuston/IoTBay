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
    private SalesAnalyser salesAnalyser = new SalesAnalyser();
    

    public Report (String reportName, String reportDescription, String startDate, String endDate, ArrayList<OrderLineItem> saleRecords) {
        this.name = reportName;
        this.description = reportDescription;
        this.startDate = startDate;
        this.endDate = endDate;
        this.saleRecords = saleRecords;
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

    public ProductSummary getTopSellingItem(ArrayList<ProductSummary> summaryArray) {
        return salesAnalyser.getTopProduct(summaryArray);
    }

    public Hashtable<String, Double> getSalesByState() {
        return salesAnalyser.getSalesBySate(saleRecords);
    }

    public Hashtable<String, Double> getSalesbyCategory() {
        return salesAnalyser.getSalesByCategory(saleRecords);
    }

    public HashMap<String, ArrayList<ProductSummary>> getSalesByCategoryByProduct(ArrayList<ProductSummary> summaryArray, ArrayList<String> categories) {
        return salesAnalyser.getSalesByCategoryByProduct(summaryArray, categories);
    }
}