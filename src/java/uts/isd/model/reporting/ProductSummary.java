package uts.isd.model.reporting;

import java.io.Serializable;

public class ProductSummary implements Serializable {
    private final String productID;
    private final String productName;
    private final String productCategory;
    private int unitsSold;
    private double productRevenue;

    public ProductSummary(String productID, String productName, String productCategory, int unitsSold, double productRevenue) {
        this.productID = productID;
        this.productName = productName;
        this.productCategory = productCategory;
        this.unitsSold = unitsSold;
        this.productRevenue = productRevenue;
    }

    public String getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public int getUnitsSold() {
        return unitsSold;
    }

    public double getProductRevenue() {
        return productRevenue;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void addUnitsSold(int unitsSold) {
        this.unitsSold += unitsSold;
    }

    public void addProductRevenue(double productRevenue) {
        this.productRevenue += productRevenue;
    }

    public void printObj() {
        System.out.println("Product ID: " + productID + "; Product Name: " + productName + "; Product Category: " + productCategory + "; Units Sold: " + unitsSold + "; Product Revenue = $" + productRevenue);
    }
}