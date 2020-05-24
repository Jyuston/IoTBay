package uts.isd.model.dao;

import uts.isd.model.Address;
import uts.isd.model.Customer;
import uts.isd.model.Order;
import uts.isd.model.PaymentInformation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class CustomerDAO implements DAO<Customer> {
    public static Connection dbConnection;

    static {
        try {
            dbConnection = new DBConnector().openConnection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    public Customer get(String email, String password) throws SQLException {
        // Setting up the initial SQL query to find the customer by email and password
        Statement st = dbConnection.createStatement();
        String getUserIdQuery =
                "SELECT USER_ID FROM ACCOUNTS " +
                        "WHERE USER_EMAIL LIKE '" + email + "' " +
                        "AND PASSWORD LIKE '" + password + "'";

        ResultSet userIDRs = st.executeQuery(getUserIdQuery);

        // If no user, return null
        if (!userIDRs.next())
            return null;

        String userID = userIDRs.getString("USER_ID");
        String getCustomerDetailsQuery =
                "SELECT * FROM ACCOUNTS A " +
                "INNER JOIN CUSTOMERS C on A.USER_ID = C.USER_ID " +
                "INNER JOIN PAYMENT_INFORMATION PI on A.USER_ID = PI.USER_ID " +
                "WHERE A.USER_ID = '" + userID + "'";

        ResultSet customerData = st.executeQuery(getCustomerDetailsQuery);

        // Move SQL cursor to the found users row
        // Don't have to check for empty result since we already found an ID
        customerData.next();

        return createCustomerObject(customerData);
    }

    @Override
    public Customer get(String accountID) throws SQLException {
        Statement st = dbConnection.createStatement();
        String getCustomerDataQuery =
                "SELECT * FROM ACCOUNTS A " +
                "INNER JOIN CUSTOMERS C on A.USER_ID = C.USER_ID " +
                "INNER JOIN PAYMENT_INFORMATION PI on A.USER_ID = PI.USER_ID " +
                "WHERE A.USER_ID = '" + accountID + "'";

        ResultSet customerRs = st.executeQuery(getCustomerDataQuery);

        // If no data, return null Customer
        if (!customerRs.next())
            return null;

        return createCustomerObject(customerRs);
    }

    @Override
    public List<Customer> getAll() throws SQLException {
        LinkedList<Customer> customers = new LinkedList<>();

        Statement st = dbConnection.createStatement();
        String getUserIdQuery =
                "SELECT * FROM ACCOUNTS A " +
                "INNER JOIN CUSTOMERS C on A.USER_ID = C.USER_ID " +
                "INNER JOIN PAYMENT_INFORMATION PI on A.USER_ID = PI.USER_ID";

        ResultSet customersRs = st.executeQuery(getUserIdQuery);

        while (customersRs.next()) {
            customers.add(createCustomerObject(customersRs));
        }

        return customers;
    }

    @Override
    public void save(Customer customer) throws SQLException {
        Statement st = dbConnection.createStatement();

        String accountInsertQuery = String.format(
                "INSERT INTO ACCOUNTS (USER_ID, USER_EMAIL, USER_F_NAME, USER_L_NAME, PASSWORD, ACCOUNT_TYPE) " +
                "VALUES ('%s', '%s', '%s', '%s', '%s', '%s')",
                customer.getID(),
                customer.getEmail(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getPassword(),
                "C"
        );
        st.executeQuery(accountInsertQuery);

        Address customerAddress = customer.getAddress();
        String customerInsertQuery = String.format(
                "INSERT INTO CUSTOMERS (USER_ID, CONTACT_NUMBER, ADDRESS_LINE_1, ADDRESS_LINE_2, SUBURB, POSTCODE, STATE, IS_ANONYMOUS) " +
                "VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
                customer.getID(),
                customer.getContactNumber(),
                customerAddress.getAddressLine1(),
                customerAddress.getAddressLine2(),
                customerAddress.getSuburb(),
                customerAddress.getPostcode(),
                customerAddress.getState(),
                "false"
        );
        st.executeQuery(customerInsertQuery);

        PaymentInformation customerPaymentInfo = customer.getPaymentInfo();
        String paymentInfoInsertQuery = String.format(
                "INSERT INTO PAYMENT_INFORMATION (USER_ID, CARD_NUMBER, CVV_NUMBER, EXPIRY_MONTH, EXPIRY_YEAR) " +
                "VALUES ('%s', '%s', '%s', '%s', '%s', )",
                customer.getID(),
                customerPaymentInfo.getCardNumber(),
                customerPaymentInfo.getCvvNumber(),
                customerPaymentInfo.getExpiryMonth(),
                customerPaymentInfo.getExpiryYear()
        );
        st.executeQuery(paymentInfoInsertQuery);
    }

    @Override
    public void update(Customer customer, String[] params) {

    }

    @Override
    public void delete(Customer customer) throws SQLException {
    }

    // Helpers
    private Customer createCustomerObject(ResultSet customerRs) throws SQLException {
        Address customerAddress = new Address(
                customerRs.getString("ADDRESS_LINE_1"),
                customerRs.getString("ADDRESS_LINE_2"),
                customerRs.getString("SUBURB"),
                customerRs.getString("POSTCODE"),
                customerRs.getString("STATE")
        );

        PaymentInformation customerPaymentInfo = new PaymentInformation(
                customerRs.getString("CARD_NUMBER"),
                customerRs.getString("CVV_NUMBER"),
                customerRs.getString("EXPIRY_MONTH"),
                customerRs.getString("EXPIRY_YEAR")
        );

        LinkedList<Order> customerOrders = new LinkedList<>();

        return new Customer(
                customerRs.getString("USER_ID"),
                customerRs.getString("USER_F_NAME"),
                customerRs.getString("USER_L_NAME"),
                customerRs.getString("USER_EMAIL"),
                customerRs.getString("PASSWORD"), // Lol
                customerRs.getString("CONTACT_NUMBER"),
                customerAddress,
                customerPaymentInfo,
                customerOrders, // empty orders for now
                customerRs.getBoolean("IS_ANONYMOUS")
        );
    }
}
