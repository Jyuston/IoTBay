package uts.isd.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.LinkedList;

public class Order implements Serializable {
    private int ID;
    private Customer customer;
    private LinkedList<OrderLineItem> orderedProducts;
    private Timestamp orderedOn;
    private double total;
    private String trackingID;
    private String status;
    private String shippingAddress;

    public Order() { }

    public int getID() { return ID; }
    public void setID(int ID) { this.ID = ID; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public LinkedList<OrderLineItem> getOrderedProducts() { return orderedProducts; }
    public void setOrderedProducts(LinkedList<OrderLineItem> orderedProducts) { this.orderedProducts = orderedProducts; }

    public Timestamp getOrderedOn() { return orderedOn; }
    public void setOrderedOn(Timestamp orderedOn) { this.orderedOn = orderedOn; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public String getTrackingID() { return trackingID; }
    public void setTrackingID(String trackingID) { this.trackingID = trackingID; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getShippingAddress() { return shippingAddress; }
    public void setShippingAddress(String shippingAddress) { this.shippingAddress = shippingAddress; }
}
