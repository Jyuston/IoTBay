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
    private final ArrayList<OrderLineItem> saleRecords = new ArrayList<>();
    private final ArrayList<ProductSummary> productSummaries = new ArrayList<>();
    private final ArrayList<String> categories = new ArrayList<>();
    private final SalesAnalyser salesAnalyser = new SalesAnalyser();
    private ProductSummary topProduct;

    public Report(String reportName, String reportDescription, String startDate, String endDate, 
        ArrayList<OrderLineItem> saleRecords, ArrayList<ProductSummary> summaries, ArrayList<String> categories) {
        this.name = reportName;
        this.description = reportDescription;
        this.startDate = startDate;
        this.endDate = endDate;
        
        if (saleRecords.size() > 0 && saleRecords != null) {
            this.saleRecords.addAll(saleRecords);
            this.productSummaries.addAll(summaries);
            this.categories.addAll(categories);
            this.topProduct = salesAnalyser.getTopProduct(summaries);
        }
    }

    public Report(String reportName, String reportDescription, String startDate, String endDate) {
        this.name = reportName;
        this.description = reportDescription;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Report(ArrayList<ProductSummary> summaries, ArrayList<String> categories) {
        this.name = "Stock Report";
        this.description = "Inventory Level Report";
        this.startDate = "";
        this.endDate = "";
        this.productSummaries.addAll(summaries);
        this.categories.addAll(categories);
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

    public final Double getTotalRevenue() {
        return salesAnalyser.getTotalSalesValue(saleRecords);
    }

    public final String getTopCategory() {
        return salesAnalyser.getTopCategory(saleRecords);
    }

    public final Double getTopCategoryRevenue() {
        return salesAnalyser.getTopCategoryRevenue(getTopCategory(), saleRecords);
    }

    public final ProductSummary getTopSellingItem() {
        return topProduct;
        //return salesAnalyser.getTopProduct(productSummaries);
    }

    public final Hashtable<String, Double> getSalesByState() {
        return salesAnalyser.getSalesBySate(saleRecords);
    }

    public final Hashtable<String, Double> getSalesByCategory() {
        return salesAnalyser.getSalesByCategory(saleRecords);
    }

    public HashMap<String, ArrayList<ProductSummary>> getSalesBreakdown() {
        return salesAnalyser.getSalesByCategoryByProduct(productSummaries, categories);
    }

    public boolean salesExist() {
        return saleRecords.size() > 0;
    }

    public HashMap<String, ArrayList<ProductSummary>> getStockReport() {
        return salesAnalyser.getStockReport(productSummaries, categories);
    }
}