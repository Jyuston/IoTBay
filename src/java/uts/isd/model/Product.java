package uts.isd.model;

import java.io.Serializable;
import java.sql.SQLException;
import uts.isd.model.dao.DAO;
import uts.isd.model.dao.ProductDAO;

public class Product implements Serializable {
    private String ID;
    private String name;
    private  String category;
    private int stock;
    private double price;
    private boolean archived;

    public Product(String name, int stock, double price, String category, boolean archived) {
        this.ID = "123abc"; // Hard coded for prototype
        this.name = name;
        this.stock = stock;
        this.price = price;
        this.category = category;
        this.archived = archived;
    }

   



    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }
    
    public static Product create(String name, int stock, double price, String category, boolean archived) {
        String ID;
        try {
            ID = ProductDAO.getNextAvailableID();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        Product createdProduct = new Product(
                name,
                stock,
                price,
                category,
                archived
        );

        try {
            DAO.save(createdProduct);
        } catch (SQLException err) {
            err.printStackTrace();
            return null;
        }

        return createdProduct;
    }
    
    
    
}







