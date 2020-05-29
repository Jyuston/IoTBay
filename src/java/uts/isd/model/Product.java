package uts.isd.model;

import java.io.Serializable;

public class Product implements Serializable {
    private int ID;
    private String name;
    private  String category;
    private int stock;
    private double price;
    private String description;
    private boolean archived;

    public Product(int ID, String name, int stock, double price, String category, String description, boolean archived) {
        this.ID = ID; 
        this.name = name;
        this.stock = stock;
        this.price = price;
        this.category = category;
        this.description = description;
        this.archived = archived;
    }

    public int getID() { return ID; }
    public void setID(int ID) { this.ID = ID; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public boolean isArchived() { return archived; }
    public void setArchived(boolean archived) { this.archived = archived; }
}







