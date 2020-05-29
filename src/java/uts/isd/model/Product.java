package uts.isd.model;

import java.io.Serializable;

public class Product implements Serializable {
    private Integer ID;
    private String name;
    private  String category;
    private int stock;
    private double price;
    private boolean archived;

    public Product(Integer ID, String name, String category, int stock, double price, boolean archived) {
        this.ID = ID;
        this.name = name;
        this.category = category;
        this.stock = stock;
        this.price = price;
        this.archived = archived;
    }

    public Integer getID() { return ID; }
    public void setID(Integer ID) { this.ID = ID; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public boolean isArchived() { return archived; }
    public void setArchived(boolean archived) { this.archived = archived; }
}
