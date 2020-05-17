package uts.isd.model;

import java.util.LinkedList;

public class Customer extends Account {
    private String contactNumber;
    private Address address;
    private PaymentInformation paymentInfo;
    private LinkedList<Order> orders;
    private boolean isAnonymous;

    public Customer(String firstName, String lastName, String email, String password, String contactNumber, Address address, PaymentInformation paymentInfo, LinkedList<Order> orders, boolean isAnonymous) {
        super(firstName, lastName, email, password);
        this.contactNumber = contactNumber;
        this.address = address;
        this.paymentInfo = paymentInfo;
        this.orders = orders;
        this.isAnonymous = isAnonymous;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public PaymentInformation getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(PaymentInformation paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public LinkedList<Order> getOrders() {
        return orders;
    }

    public void setOrders(LinkedList<Order> orders) {
        this.orders = orders;
    }

    public boolean isAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(boolean anonymous) {
        isAnonymous = anonymous;
    }
}
