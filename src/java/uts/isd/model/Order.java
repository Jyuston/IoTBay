package uts.isd.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.LinkedList;

public class Order implements Serializable {
    private String ID;
    private Customer customer;
    private LinkedList<ProductSnapshot> orderedProducts;
    private Date orderedOn;
    private int orderTotal;
    private String trackingID;
    private String status;
    private String shippingAddress;
    private PaymentInformation paymentInfo;

    public Order(Customer customer, LinkedList<ProductSnapshot> orderedProducts, int orderTotal, String shippingAddress, PaymentInformation paymentInfo) {
        this.ID = "123abc"; // Hard coded for prototype
        this.customer = customer;
        this.orderedProducts = orderedProducts;
        this.orderTotal = orderTotal;
        this.shippingAddress = shippingAddress;
        this.paymentInfo = paymentInfo;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LinkedList<ProductSnapshot> getOrderedProducts() {
        return orderedProducts;
    }

    public void setOrderedProducts(LinkedList<ProductSnapshot> orderedProducts) {
        this.orderedProducts = orderedProducts;
    }

    public PaymentInformation getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(PaymentInformation paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Date getOrderedOn() {
        return orderedOn;
    }

    public void setOrderedOn(Date orderedOn) {
        this.orderedOn = orderedOn;
    }

    public int getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(int orderTotal) {
        this.orderTotal = orderTotal;
    }

    public String getTrackingID() {
        return trackingID;
    }

    public void setTrackingID(String trackingID) {
        this.trackingID = trackingID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
}
