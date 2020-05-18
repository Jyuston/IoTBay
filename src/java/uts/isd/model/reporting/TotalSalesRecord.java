package uts.isd.model.reporting;

import java.io.Serializable;

public class TotalSalesRecord implements Serializable {
    private final String productID;
    private final String productName;
    private final String productCategory;
    private final int quantityOrdered;
    private final double productPrice;
    private final String deliveryAddress;

    public TotalSalesRecord (String productID, String productName, String productCategory, int quantityOrdered, double productPrice, String deliveryAddress) {
        this.productID = productID;
        this.productName = productName;
        this.productCategory = productCategory;
        this.quantityOrdered = quantityOrdered;
        this.productPrice = productPrice;
        this.deliveryAddress = deliveryAddress;
    }

    public String getProductID() {
        return productID;
    }

    public int getQuantityOrdered() {
        return quantityOrdered;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public String getState() {
        String s[] = deliveryAddress.split("-");

        return s[s.length - 1];
    }

    @Override public String toString() {
        return "Product ID: " + productID + "; Product Name: " + productName + "; Product Category: " + productCategory + "; Quanitiy: " + quantityOrdered + "; Price: " + productPrice + "; Address: " + deliveryAddress;
    }
}