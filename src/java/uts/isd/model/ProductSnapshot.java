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
}
