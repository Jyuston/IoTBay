package uts.isd.model;

import uts.isd.model.dao.CustomerDAO;
import uts.isd.model.dao.DBConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class Customer extends Account {
    private String contactNumber;
    private Address address;
    private PaymentInformation paymentInfo;
    private LinkedList<Order> orders;
    private boolean isAnonymous;

    public Customer(String ID, String firstName, String lastName, String email, String password, String contactNumber, Address address, PaymentInformation paymentInfo, LinkedList<Order> orders, boolean isAnonymous) {
        super(ID, firstName, lastName, email, password);
        this.contactNumber = contactNumber;
        this.address = address;
        this.paymentInfo = paymentInfo;
        this.orders = orders;
        this.isAnonymous = isAnonymous;
    }

    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }

    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }

    public PaymentInformation getPaymentInfo() { return paymentInfo; }
    public void setPaymentInfo(PaymentInformation paymentInfo) { this.paymentInfo = paymentInfo; }

    public LinkedList<Order> getOrders() { return orders; }
    public void setOrders(LinkedList<Order> orders) { this.orders = orders; }

    public boolean isAnonymous() { return isAnonymous; }
    public void setAnonymous(boolean anonymous) { isAnonymous = anonymous; }

    public static Customer create(String firstName, String lastName, String email, String password, String contactNumber, Address address, PaymentInformation paymentInfo) {
        String ID;
        try {
            ID = generateID();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        return new Customer(
                ID,
                firstName,
                lastName,
                email,
                password,
                contactNumber,
                address,
                paymentInfo,
                new LinkedList<>(),
                false
        );
    }

    private static String generateID() throws SQLException, ClassNotFoundException {
        Connection connection = new DBConnector().openConnection();
        Statement st = connection.createStatement();

        String accountIDsQuery = "SELECT USER_ID FROM ACCOUNTS";
        ResultSet accountIDsRs = st.executeQuery(accountIDsQuery);

        if (!accountIDsRs.last())
            return "U-1";

        String lastID = accountIDsRs.getString("USER_ID");
        int lastNumber = Integer.parseInt(lastID.substring(2));
        return "U-" + (lastNumber + 1);
    }
}
