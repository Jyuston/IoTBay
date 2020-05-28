package uts.isd.model.dao;

import uts.isd.model.Address;
import uts.isd.model.Customer;
import uts.isd.model.Order;
import uts.isd.model.PaymentInformation;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CustomerDAO {
    public static final Connection dbConnection = DBConnector.getConnection();

    public static Customer get(String email, String password) throws SQLException {
        Statement st = dbConnection.createStatement();
        String getUserIdQuery =
                "SELECT * FROM ACCOUNTS A " +
                "INNER JOIN CUSTOMERS C on A.ID = C.ID " +
                "INNER JOIN PAYMENT_INFORMATION PI on C.ID = PI.CUSTOMER_ID " +
                "WHERE A.EMAIL LIKE '" + email + "' " +
                "AND A.PASSWORD LIKE '" + password + "'";

        ResultSet customerRs = st.executeQuery(getUserIdQuery);

        // If no table rows, return null
        if (!customerRs.next())
            return null;

        return createCustomerObject(customerRs);
    }

    public static Customer get(String accountID) throws SQLException {
        Statement st = dbConnection.createStatement();
        String getCustomerDataQuery =
                "SELECT * FROM ACCOUNTS A " +
                "INNER JOIN CUSTOMERS C on A.ID = C.ID " +
                "INNER JOIN PAYMENT_INFORMATION PI on C.ID = PI.CUSTOMER_ID " +
                "WHERE C.ID = '" + accountID + "'";

        ResultSet customerRs = st.executeQuery(getCustomerDataQuery);

        // If no table rows, return null
        if (!customerRs.next())
            return null;

        return createCustomerObject(customerRs);
    }

    public static List<Customer> getAll() throws SQLException {
        LinkedList<Customer> customers = new LinkedList<>();

        Statement st = dbConnection.createStatement();
        String getUserIdQuery =
                "SELECT * FROM ACCOUNTS A " +
                "INNER JOIN CUSTOMERS C on A.ID = C.ID " +
                "INNER JOIN PAYMENT_INFORMATION PI on C.ID = PI.CUSTOMER_ID";

        ResultSet customersRs = st.executeQuery(getUserIdQuery);

        while (customersRs.next()) {
            customers.add(createCustomerObject(customersRs));
        }

        return customers;
    }

    public static void save(Customer customer) throws SQLException {
        // Write to account table and get Auto ID that was generated by SQL Server
        int newID = AccountDAO.save(customer);

        // Set to the new customer object instance
        customer.setID(newID);

        Address customerAddress = customer.getAddress();
        PreparedStatement customerInsertSt = dbConnection.prepareStatement(
                "INSERT INTO CUSTOMERS (ID, ADDRESS_LINE_1, ADDRESS_LINE_2, SUBURB, STATE, POSTCODE, IS_ANONYMOUS) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)"
        );
        customerInsertSt.setInt(1, newID);
        customerInsertSt.setString(2, customerAddress.getAddressLine1());
        customerInsertSt.setString(3, customerAddress.getAddressLine2());
        customerInsertSt.setString(4, customerAddress.getSuburb());
        customerInsertSt.setString(5, customerAddress.getState());
        customerInsertSt.setString(6, customerAddress.getPostcode());
        customerInsertSt.setBoolean(7, customer.isAnonymous());
        customerInsertSt.executeUpdate();

        // Payment Info is null when registering.
        // Must be added using update().
        PreparedStatement paymentInfoInsertSt = dbConnection.prepareStatement(
                "INSERT INTO PAYMENT_INFORMATION (CUSTOMER_ID, CARD_NUMBER, CVV_NUMBER, EXPIRY_MONTH, EXPIRY_YEAR) " +
                "VALUES (?, null, null, null, null)"
        );
        paymentInfoInsertSt.setInt(1, newID);
        paymentInfoInsertSt.executeUpdate();
    }

    public static void update(Customer customer, String[] params) {

    }

    public static void delete(Customer customer) {
    }

    // Helpers
    private static Customer createCustomerObject(ResultSet customerRs) throws SQLException {
        Address customerAddress = new Address(
                customerRs.getString("ADDRESS_LINE_1"),
                customerRs.getString("ADDRESS_LINE_2"),
                customerRs.getString("SUBURB"),
                customerRs.getString("POSTCODE"),
                customerRs.getString("STATE")
        );

        // What happens if user doesn't have payment info?
        PaymentInformation customerPaymentInfo = new PaymentInformation(
                customerRs.getString("CARD_NUMBER"),
                customerRs.getString("CVV_NUMBER"),
                customerRs.getString("EXPIRY_MONTH"),
                customerRs.getString("EXPIRY_YEAR")
        );

        // THIS SHOULD GET THE CUSTOMERS ORDERS FROM RESULT SET, NOT MAKE A EMPTY LIST
        LinkedList<Order> customerOrders = new LinkedList<>();

        return new Customer(
                customerRs.getInt("ID"),
                customerRs.getString("F_NAME"),
                customerRs.getString("L_NAME"),
                customerRs.getString("EMAIL"),
                customerRs.getString("PASSWORD"),
                customerRs.getString("CONTACT_NUMBER"),
                customerRs.getBoolean("IS_ACTIVE"),
                customerAddress,
                customerPaymentInfo,
                customerOrders, // empty orders for now
                customerRs.getBoolean("IS_ANONYMOUS")
        );
    }
}
