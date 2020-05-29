package uts.isd.model;

import java.io.Serializable;

public class ProductSnapshot implements Serializable {
    private Product current;
    private double purchasedPrice;
    private double unitPrice;
    private int quantityOrdered;

    public ProductSnapshot(Product current, double purchasedPrice, double unitPrice, int quantityOrdered) {
        this.current = current;
        this.purchasedPrice = purchasedPrice;
        this.unitPrice = unitPrice;
        this.quantityOrdered = quantityOrdered;
    }

    public Product getCurrent() { return current; }
    public void setCurrent(Product current) { this.current = current; }

    public double getPurchasedPrice() { return purchasedPrice; }
    public void setPurchasedPrice(double purchasedPrice) { this.purchasedPrice = purchasedPrice; }

    public double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(double unitPrice) { this.unitPrice = unitPrice; }

    public int getQuantityOrdered() { return quantityOrdered; }
    public void setQuantityOrdered(int quantityOrdered) { this.quantityOrdered = quantityOrdered; }
}
