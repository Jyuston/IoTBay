/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model;

import java.io.Serializable;

/**
 *
 * @author denni
 */
public class ProductSnapshot implements Serializable {
    private int productId;    
    private int quantityOrdered;
    private double unitPrice;
    private double purchasedPrice;

    // Constructor
    public ProductSnapshot(int productId, int quantityOrdered, double unitPrice, double purchasedPrice) {
        this.productId = productId;
        this.quantityOrdered = quantityOrdered;
        this.unitPrice = unitPrice;
        this.purchasedPrice = purchasedPrice;
    }

    // Getters and Setters
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantityOrdered() {
        return quantityOrdered;
    }

    public void setQuantityOrdered(int quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getPurchasedPrice() {
        return purchasedPrice;
    }

    public void setPurchasedPrice(double purchasedPrice) {
        this.purchasedPrice = purchasedPrice;
    }
    
}
