package uts.isd.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.LinkedList;

public class Order implements Serializable {
    private int ID;
    private Customer customer;
    private LinkedList<ProductSnapshot> orderedProducts;
    private Date orderedOn;
    private int total;
    private String trackingID;
    private String status;
    private String shippingAddress;

    public Order() { }

    public int getID() { return ID; }
    public void setID(int ID) { this.ID = ID; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public LinkedList<ProductSnapshot> getOrderedProducts() { return orderedProducts; }
    public void setOrderedProducts(LinkedList<ProductSnapshot> orderedProducts) { this.orderedProducts = orderedProducts; }

    public Date getOrderedOn() { return orderedOn; }
    public void setOrderedOn(Date orderedOn) { this.orderedOn = orderedOn; }

    public int getTotal() { return total; }
    public void setTotal(int total) { this.total = total; }

    public String getTrackingID() { return trackingID; }
    public void setTrackingID(String trackingID) { this.trackingID = trackingID; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getShippingAddress() { return shippingAddress; }
    public void setShippingAddress(String shippingAddress) { this.shippingAddress = shippingAddress; }
}
