package uts.isd.model;

import java.util.LinkedList;

public class Customer extends Account {
    private Address address;
    private PaymentInformation paymentInfo;
    private LinkedList<Order> orders;
    private boolean anonymous;

    public Customer(Integer ID, String firstName, String lastName, String email, String password, String contactNumber, boolean active, Address address, PaymentInformation paymentInfo, LinkedList<Order> orders, boolean anonymous) {
        super(ID, firstName, lastName, email, password, contactNumber, active);
        this.address = address;
        this.paymentInfo = paymentInfo;
        this.orders = orders;
        this.anonymous = anonymous;
    }

    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }

    public PaymentInformation getPaymentInfo() { return paymentInfo; }
    public void setPaymentInfo(PaymentInformation paymentInfo) { this.paymentInfo = paymentInfo; }

    public LinkedList<Order> getOrders() { return orders; }
    public void setOrders(LinkedList<Order> orders) { this.orders = orders; }

    public boolean isAnonymous() { return anonymous; }
    public void setAnonymous(boolean anonymous) { this.anonymous = anonymous; }
}
