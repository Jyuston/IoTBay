package uts.isd.model.reporting;

import java.io.Serializable;

public class ProductSummary implements Serializable {
    private final String id;
    private final String name;
    private final String category;
    private int unitsSold;
    private double revenue;

    public ProductSummary(String productID, String productName, String productCategory, int unitsSold, double productRevenue) {
        this.id = productID;
        this.name = productName;
        this.category = productCategory;
        this.unitsSold = unitsSold;
        this.revenue = productRevenue;
    }

    public String getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getUnitsSold() {
        return unitsSold;
    }

    public double getRevenue() {
        return revenue;
    }

    public String getCategory() {
        return category;
    }

    public void addUnitsSold(int unitsSold) {
        this.unitsSold += unitsSold;
    }

    public void addProductRevenue(double productRevenue) {
        this.revenue += productRevenue;
    }

}