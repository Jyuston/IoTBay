package uts.isd.model;

import java.io.Serializable;

public class ProductSnapshot implements Serializable {
    private Product currentProduct;
    private double purchasedPrice;
    private double unitPrice;
    private int quantityOrdered;

    public ProductSnapshot(double purchasedPrice, double unitPrice, int quantityOrdered) {
        this.purchasedPrice = purchasedPrice;
        this.unitPrice = unitPrice;
        this.quantityOrdered = quantityOrdered;
    }

    public Product getCurrentProduct() {
        return currentProduct;
    }

    public void setCurrentProduct(Product currentProduct) {
        this.currentProduct = currentProduct;
    }

    public double getPurchasedPrice() {
        return purchasedPrice;
    }

    public void setPurchasedPrice(double purchasedPrice) {
        this.purchasedPrice = purchasedPrice;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantityOrdered() {
        return quantityOrdered;
    }

    public void setQuantityOrdered(int quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }
}
