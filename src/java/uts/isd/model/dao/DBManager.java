package uts.isd.model.dao;

import uts.isd.model.Customer;
import uts.isd.model.PaymentInformation;
import uts.isd.model.Address;
import uts.isd.model.Order;

import java.sql.*;
import java.util.*;
import java.util.Random;

/*
 * DBManager is the primary DAO class to interact with the database.
 * Complete the existing methods of this classes to perform CRUD operations with the db.
 */

public class DBManager {
    private Statement st;

    public DBManager(Connection conn) throws SQLException {
        st = conn.createStatement();
    }

    // Find a customer by email and password in the database
    public Customer findUserByEmailPass(String email, String password) throws SQLException {
        // Setting up the inital SQL query to find the customer by email and password
        String getUserIdQuery = ("SELECT USER_ID FROM ACCOUNTS WHERE USER_EMAIL LIKE '" + email + "' AND PASSWORD LIKE '" + password + "'");
        // Execute this query using the statement field
        ResultSet userIDResult = st.executeQuery(getUserIdQuery);
      
        // If no user, return null
        if (!userIDResult.next())
            return null;
             
        String getCustomerDetailsQuery = ("SELECT * FROM ACCOUNTS INNER JOIN CUSTOMERS on ACCOUNTS.USER_ID = CUSTOMERS.USER_ID INNER JOIN PAYMENT_INFORMATION on ACCOUNTS.USER_ID = PAYMENT_INFORMATION.USER_ID WHERE ACCOUNTS.USER_ID = '" + userIDResult.getString("USER_ID") + "'");
        ResultSet customerDetails = st.executeQuery(getCustomerDetailsQuery);
        
        // Move SQL cursor to the found users row
        customerDetails.next();

        // This while isn't doing anything since it always returns but im not 100% sure
        // while (customerDetails.next()) {

        Address customerAddress = new Address(
                customerDetails.getString("ADDRESS_LINE_1"),
                customerDetails.getString("ADDRESS_LINE_2"),
                customerDetails.getString("SUBURB"),
                customerDetails.getString("POSTCODE"),
                customerDetails.getString("STATE")
        );

        PaymentInformation customerPaymentInfo = new PaymentInformation(
                customerDetails.getString("CARD_NUMBER"),
                customerDetails.getString("CVV_NUMBER"),
                customerDetails.getString("EXPIRY_MONTH"),
                customerDetails.getString("EXPIRY_YEAR")
        );

        LinkedList<Order> customerOrders = new LinkedList<>();

        return new Customer(
                "TE",
                customerDetails.getString("USER_F_NAME"),
                customerDetails.getString("USER_L_NAME"),
                customerDetails.getString("USER_EMAIL"),
                customerDetails.getString("PASSWORD"), // Lol
                customerDetails.getString("CONTACT_NUMBER"),
                customerAddress,
                customerPaymentInfo,
                customerOrders, // empty orders for now
                customerDetails.getBoolean("IS_ANONYMOUS")
        );

        // } (End of while loop above)
    }

    // Add a user-data into the database and return the object
    public Customer addCustomer(String firstName, String lastName, String email, String password, String contactNumber, Address address, PaymentInformation paymentInfo) throws SQLException {

        //Generate a new User ID (performed in this class for encapsulation purposes)
        String userID = generateID();

        // Build the insertation statement to insert the record into ACCOUNTS
        String insertToAccount = "INSERT INTO ACCOUNTS (USER_ID, USER_EMAIL, USER_F_NAME, USER_L_NAME, ACCOUNT_TYPE, PASSWORD) VALUES ('" + userID + "', '" + email + "', '" + firstName + "', '" + lastName + "', '" + "C', '" + password + "')";

        // Execute the above query
        st.executeUpdate(insertToAccount);

        // Built the insertion statement to insert the record into CUSTOMERS
        String insertToCustomer = "INSERT INTO CUSTOMERS (USER_ID, CONTACT_NUMBER, ADDRESS_LINE_1, ADDRESS_LINE_2, SUBURB, POSTCODE, STATE, IS_ANONYMOUS) VALUES ('" + userID + "', '" + contactNumber + "', '" + address.getAddressLine1() + "', '" + address.getAddressLine2() + "', '" + address.getSuburb() + "', '" + address.getPostcode() + "', '" + address.getState() + "', 'false')";

        // Execute the above query
        st.executeUpdate(insertToCustomer);

        // Build the insertion statement to insert the record into PAYMENT_INFORMATION
        String insertToPaymentInformation = "INSERT INTO PAYMENT_INFORMATION (USER_ID, CARD_NUMBER, CVV_NUMBER, EXPIRY_MONTH, EXPIRY_YEAR) VALUES ('" + userID + "', '" + paymentInfo.getCardNumber() + "', '" + paymentInfo.getCvvNumber() + "', '" + paymentInfo.getExpiryMonth() + "', '" + paymentInfo.getExpiryYear() + "')";
        st.executeUpdate(insertToPaymentInformation);

        // Create new orders list
        LinkedList<Order> orders = new LinkedList<Order>();

        // Return the built Customer object
        return new Customer(
                "TE",
                firstName,
                lastName,
                email,
                password,
                contactNumber,
                address,
                paymentInfo,
                orders, // empty orders for now
                false
        );
    }

    //#region ID Generater Supporting Code
    private String generateID() throws SQLException {
        // Build the select query that will retrieve all USER_IDs from the database
        String getIDs = "SELECT USER_ID FROM ACCOUNTS";

        // Execute the query against the database
        ResultSet userIDResult = st.executeQuery(getIDs);

        // Instiate a list to store the IDs (for easy iteration)
        ArrayList<String> userIDs = new ArrayList<String>();

        // Add the IDs to the list
        while (userIDResult.next()) {
            userIDs.add(userIDResult.getString(1));
        }

        // Instantiate a new random number generator object
        Random rand = new Random();

        // Set the upper bound of the object
        int upperbound = 99999999;

        // Generate the random number
        int random = rand.nextInt(upperbound);

        // Check that the ID is in fact unique (using a helper method)
        while (!checkUnique(Integer.toString(random), userIDs)) {
            random = rand.nextInt(upperbound);
        }

        // Return the final USER_ID for the new record
        return "U-" + random;
    }

    private boolean checkUnique(String value, ArrayList<String> collection) {
        // Checking to see if the supplied value already exists
        return !collection.contains(value);
    }
    //#endregion

    // update a user details in the database
    public void updateUser(String email, String name, String password, String gender, String favcol) throws SQLException {
        // code for update-operation

    }

    // delete a user from the database
    public void deleteUser(String email) throws SQLException {
        // code for delete-operation

    }

}