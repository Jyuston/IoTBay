package uts.isd.model.reporting;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

public class Report implements Serializable {
    private String name;
    private String description;
    private String startDate;
    private String endDate;
    private final ArrayList<OrderLineItem> saleRecords = new ArrayList<>();
    private final ArrayList<ProductSummary> productSummaries = new ArrayList<>();
    private final ArrayList<String> categories = new ArrayList<>();
    private final SalesAnalyser salesAnalyser = new SalesAnalyser();
    private final ArrayList<ProductSummary> topProducts = new ArrayList<>();

    // Standard constructor for a sales report
    public Report(String reportName, String reportDescription, String startDate, String endDate, 
        ArrayList<OrderLineItem> saleRecords, ArrayList<ProductSummary> summaries, ArrayList<String> categories) {
        this.name = reportName;
        this.description = reportDescription;
        this.startDate = startDate;
        this.endDate = endDate;
        
        // Prevents null pointer exception errors if no sales records were found for the selected period
        if (saleRecords.size() > 0 && saleRecords != null) {
            this.saleRecords.addAll(saleRecords);
            this.productSummaries.addAll(summaries);
            this.categories.addAll(categories);
            topProducts.addAll(salesAnalyser.getTopProduct(summaries));
        }
    }

    // Constructor for stock report
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

    public final ArrayList<ProductSummary> getTopSellingItem() {
        return topProducts;
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