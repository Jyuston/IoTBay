package uts.isd.model.dao;

import uts.isd.model.*;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CustomerDAO {
    public static Customer get(String email, String password) throws SQLException, DAOException {
        String query =
                "SELECT * FROM ACCOUNTS A " +
                "INNER JOIN CUSTOMERS C on A.ID = C.ID " +
                "INNER JOIN PAYMENT_INFORMATION PI on C.ID = PI.CUSTOMER_ID " +
                "WHERE A.EMAIL LIKE ? AND A.PASSWORD LIKE ?";

        PreparedStatement st = DAOUtils.prepareStatement(query, false, email, password);
        ResultSet customerRs = st.executeQuery();

        // If no table rows, return null
        if (!customerRs.next())
            throw new DAOException("No Customer found. Incorrect Email or Password.");

        return createCustomerObject(customerRs);
    }

    public static Customer get(int accountID) throws SQLException, DAOException {
        String getCustomerDataQuery =
                "SELECT * FROM ACCOUNTS A " +
                "INNER JOIN CUSTOMERS C on A.ID = C.ID " +
                "INNER JOIN PAYMENT_INFORMATION PI on C.ID = PI.CUSTOMER_ID " +
                "WHERE C.ID = ?";

        PreparedStatement getCustomerDataSt = DAOUtils.prepareStatement(getCustomerDataQuery, false, accountID);
        ResultSet customerRs = getCustomerDataSt.executeQuery();

        // If no table rows, return null
        if (!customerRs.next())
            throw new DAOException("No Customer with that ID exists.");

        return createCustomerObject(customerRs);
    }

    public static List<Customer> getAll() throws SQLException {
        LinkedList<Customer> customers = new LinkedList<>();

        String getAllUsersQuery =
                "SELECT * FROM ACCOUNTS A " +
                "INNER JOIN CUSTOMERS C on A.ID = C.ID " +
                "INNER JOIN PAYMENT_INFORMATION PI on C.ID = PI.CUSTOMER_ID";

        PreparedStatement getAllUsersSt = DAOUtils.prepareStatement(getAllUsersQuery, false);
        ResultSet customersRs = getAllUsersSt.executeQuery();

        while (customersRs.next()) {
            customers.add(createCustomerObject(customersRs));
        }

        return customers;
    }

    public static void save(Customer customer) throws SQLException, DAOException {
        // Write to account table and get Auto ID that was generated by SQL Server
        int newID = AccountDAO.save(customer);

        // Set to the new customer object instance
        customer.setID(newID);

        Address customerAddress = customer.getAddress();
        String customerInsertQuery =
                "INSERT INTO CUSTOMERS (ID, ADDRESS_LINE_1, ADDRESS_LINE_2, SUBURB, STATE, POSTCODE, IS_ANONYMOUS) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement customerInsertSt = DAOUtils.prepareStatement(customerInsertQuery, false,
                newID,
                customerAddress.getAddressLine1(),
                customerAddress.getAddressLine2(),
                customerAddress.getSuburb(),
                customerAddress.getState(),
                customerAddress.getPostcode(),
                customer.isAnonymous()
        );

        int rowsChanged = customerInsertSt.executeUpdate();
        if (rowsChanged == 0)
            throw new DAOException("Failed to create customer profile. Please try again.");

        // Payment Info is null when registering.
        // Must be added using update().
        String paymentInfoInsertQuery =
                "INSERT INTO PAYMENT_INFORMATION (CUSTOMER_ID, CARD_NUMBER, CVV_NUMBER, EXPIRY_MONTH, EXPIRY_YEAR) " +
                "VALUES (?, null, null, null, null)";

        PreparedStatement paymentInfoInsertSt = DAOUtils.prepareStatement(paymentInfoInsertQuery, false, newID);
        rowsChanged = paymentInfoInsertSt.executeUpdate();
        if (rowsChanged == 0)
            throw new DAOException("Failed to add customer payment information. Please try again.");
    }

    /**
     * Update a single account from the database.
     *
     * @param customer The instantiated account to update. Must have an ID.
     */
    public static void update(Customer customer) throws SQLException, DAOException {
        AccountDAO.update(customer);

        String updateQuery =
                "UPDATE CUSTOMERS SET ADDRESS_LINE_1 = ?,ADDRESS_LINE_2 = ?, SUBURB = ?, STATE = ?,POSTCODE = ?, IS_ANONYMOUS = ? " +
                "WHERE ID = ?";

        Address address = customer.getAddress();

        PreparedStatement updateSt = DAOUtils.prepareStatement(updateQuery, false,
                address.getAddressLine1(),
                address.getAddressLine2(),
                address.getSuburb(),
                address.getState(),
                address.getPostcode(),
                customer.isAnonymous(),
                customer.getID()
        );

        int rowsChanged = updateSt.executeUpdate();
        if (rowsChanged == 0)
            throw new DAOException("Failed to update Customer details. Please try again.");
    }
    // Helpers
    private static Customer createCustomerObject(ResultSet customerRs) throws SQLException {
        Address address = new Address();
        address.setAddressLine1(customerRs.getString("ADDRESS_LINE_1"));
        address.setAddressLine2(customerRs.getString("ADDRESS_LINE_2"));
        address.setSuburb(customerRs.getString("SUBURB"));
        address.setPostcode(customerRs.getString("POSTCODE"));
        address.setState(customerRs.getString("STATE"));

        PaymentInformation paymentInfo = new PaymentInformation();
        paymentInfo.setCardNumber(customerRs.getString("CARD_NUMBER"));
        paymentInfo.setCvvNumber(customerRs.getString("CVV_NUMBER"));
        paymentInfo.setExpiryMonth(customerRs.getString("EXPIRY_MONTH"));
        paymentInfo.setExpiryYear(customerRs.getString("EXPIRY_YEAR"));

        // THIS SHOULD GET THE CUSTOMERS ORDERS FROM RESULT SET, NOT MAKE A EMPTY LIST
        LinkedList<Order> customerOrders = new LinkedList<>();

        Customer customer = new Customer();
        customer.setID(customerRs.getInt("ID"));
        customer.setFirstName(customerRs.getString("F_NAME"));
        customer.setLastName(customerRs.getString("L_NAME"));
        customer.setEmail(customerRs.getString("EMAIL"));
        customer.setPassword(customerRs.getString("PASSWORD"));
        customer.setContactNumber(customerRs.getString("CONTACT_NUMBER"));
        customer.setActive(customerRs.getBoolean("IS_ACTIVE"));
        customer.setAddress(address);
        customer.setPaymentInfo(paymentInfo);
        customer.setOrders(customerOrders);
        customer.setAnonymous(customerRs.getBoolean("IS_ANONYMOUS"));

        return customer;
    }
}
