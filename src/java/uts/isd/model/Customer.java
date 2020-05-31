package uts.isd.model;

import java.util.LinkedList;

public class Customer extends Account {
    private Address address;
    private PaymentInformation paymentInfo;
    private LinkedList<Order> orders;
    private boolean anonymous;

    public Customer() { }

    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }

    public PaymentInformation getPaymentInfo() { return paymentInfo; }
    public void setPaymentInfo(PaymentInformation paymentInfo) { this.paymentInfo = paymentInfo; }

    public LinkedList<Order> getOrders() { return orders; }
    public void setOrders(LinkedList<Order> orders) { this.orders = orders; }

    public boolean isAnonymous() { return anonymous; }
    public void setAnonymous(boolean anonymous) { this.anonymous = anonymous; }
}
