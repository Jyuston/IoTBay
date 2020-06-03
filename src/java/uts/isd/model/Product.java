package uts.isd.model;

import java.io.Serializable;

public class Product implements Serializable {
    private int ID;
    private String name;
    private String category;
    private String description;
    private int stock;
    private double price;
    private boolean archived;

    public Product() { }

    public int getID() { return ID; }
    public void setID(int ID) { this.ID = ID; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public boolean isArchived() { return archived; }
    public void setArchived(boolean archived) { this.archived = archived; }
}
