package uts.isd.model;

import java.util.LinkedList;

public class Customer extends Account {
    private Address address;
    private PaymentInformation paymentInfo;
    private LinkedList<Order> orders;
    private boolean isAnonymous;

    public Customer(String ID, String firstName, String lastName, String email, String password, String contactNumber, Address address, PaymentInformation paymentInfo, LinkedList<Order> orders, boolean isAnonymous) {
        super(ID, firstName, lastName, email, password, contactNumber);
        this.address = address;
        this.paymentInfo = paymentInfo;
        this.orders = orders;
        this.isAnonymous = isAnonymous;
    }

    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }

    public PaymentInformation getPaymentInfo() { return paymentInfo; }
    public void setPaymentInfo(PaymentInformation paymentInfo) { this.paymentInfo = paymentInfo; }

    public LinkedList<Order> getOrders() { return orders; }
    public void setOrders(LinkedList<Order> orders) { this.orders = orders; }

    public boolean isAnonymous() { return isAnonymous; }
    public void setAnonymous(boolean anonymous) { isAnonymous = anonymous; }

//    public static Customer create(String firstName, String lastName, String email, String password, String contactNumber, Address address, PaymentInformation paymentInfo) {
//        String ID;
//        try {
//            ID = AccountDAO.getNextAvailableID();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//
//        Customer createdCustomer = new Customer(
//                ID,
//                firstName,
//                lastName,
//                email,
//                password,
//                contactNumber,
//                address,
//                paymentInfo,
//                new LinkedList<>(),
//                false
//        );
//
//        try {
//            DAO.save(createdCustomer);
//        } catch (SQLException err) {
//            err.printStackTrace();
//            return null;
//        }
//
//        return createdCustomer;
//    }
}
