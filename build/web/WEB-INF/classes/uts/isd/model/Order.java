/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author denni
 */
public class Order implements Serializable {
    private int orderID;
    private int customerID;
    private String customerEmail;
    private String deliveryAddress;
    private String trackingNumber;
    private double orderTotal;
    private String orderStatus;
    private LocalDateTime orderDateTime;
    private String cardNumber;    
    private ArrayList<ProductSnapshot> orderedProducts = new ArrayList<>();

    //Constructor
    public Order(int orderID, int customerID, String customerEmail, String deliveryAddress, String trackingNumber, double orderTotal, String orderStatus, LocalDateTime orderDateTime, String cardNumber) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.customerEmail = customerEmail;
        this.deliveryAddress = deliveryAddress;
        this.trackingNumber = trackingNumber;
        this.orderTotal = orderTotal;
        this.orderStatus = orderStatus;
        this.orderDateTime = orderDateTime;
        this.cardNumber = cardNumber;
    }

    // Getters and Setters
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(LocalDateTime orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public ArrayList<ProductSnapshot> getOrderedProducts() {
        return orderedProducts;
    }

    public void setOrderedProducts(ArrayList<ProductSnapshot> orderedProducts) {
        this.orderedProducts = orderedProducts;
    }
}
