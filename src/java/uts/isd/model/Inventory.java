package uts.isd.model;

import java.io.Serializable;
import java.util.LinkedList;

public class Inventory implements Serializable {
    private LinkedList<Product> products;
    private int noOfProducts;

    public Inventory() {
        this.products = new LinkedList<>();
        this.noOfProducts = 0;
    }

    public LinkedList<Product> getProducts() {
        return products;
    }

    public void setProducts(LinkedList<Product> products) {
        this.products = products;
    }

    public int getNoOfProducts() {
        return noOfProducts;
    }

    public void setNoOfProducts(int noOfProducts) {
        this.noOfProducts = noOfProducts;
    }
}
