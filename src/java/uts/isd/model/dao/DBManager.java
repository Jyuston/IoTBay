/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model.dao;

import uts.isd.model.User;
import uts.isd.model.Customer;
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
   public Customer findUser(String email, String password) throws SQLException {
      // Setting up the inital SQL query to find the customer by email and password
      String getUserIdQuery = ("SELECT USER_ID FROM ACCOUNTS WHERE USER_EMAIL LIKE '" + email + "' AND PASSWORD LIKE '" + password + "'");
      // Execute this query using the statement field
      ResultSet userIDResult = st.executeQuery(getUserIdQuery);

      // Increment the result set
      // search the ResultSet for a user using the parameters
      userIDResult.next();
      
      if (userIDResult.getString(1) != null) {
         String getCustomerDetailsQuery = ("SELECT * FROM ACCOUNTS INNER JOIN CUSTOMERS on ACCOUNTS.USER_ID = CUSTOMERS.USER_ID INNER JOIN PAYMENT_INFORMATION on ACCOUNTS.USER_ID = PAYMENT_INFORMATION.USER_ID WHERE ACCOUNTS.USER_ID = '" + userIDResult.getString(1) + "'");
         ResultSet customerDetails = st.executeQuery(getCustomerDetailsQuery);
  
         while (customerDetails.next()) {
            
            Customer cus = new Customer(
               // First name, last name, password, email, contact number
               customerDetails.getString(3), customerDetails.getString(4), customerDetails.getString(6), customerDetails.getString(2), customerDetails.getString(8),
               // Address line 1, address line 2, suburb, post code, state
               customerDetails.getString(9), customerDetails.getString(10), customerDetails.getString(11), customerDetails.getString(13), customerDetails.getString(12),
               // Card number, cvv, expiry month, expiry year
               customerDetails.getString(15), customerDetails.getString(17), customerDetails.getString(18), customerDetails.getString(19)
            );

            return cus;
         }
      }

      return null;
   }

   // Add a user-data into the database and return the object
   public Customer addCustomer(String firstName, String lastName, String password, String email, String contactNumber, String addressLine1, String addressLine2, String suburb, String postCode, String state, String cardNumber, String cvv, String expiryMonth, String expiryYear) throws SQLException { 

      //Generate a new User ID (performed in this class for encapsulation purposes)
      String userID = generateID();

      // Build the insertation statement to insert the record into ACCOUNTS
      String insertToAccount = "INSERT INTO ACCOUNTS (USER_ID, USER_EMAIL, USER_F_NAME, USER_L_NAME, ACCOUNT_TYPE, PASSWORD) VALUES ('" + userID + "', '" + email + "', '" + firstName + "', '" + lastName + "', '" + "C', '" + password + "')";
      
      // Execute the above query
      st.executeUpdate(insertToAccount);

      // Built the insertion statement to insert the record into CUSTOMERS
      String insertToCustomer = "INSERT INTO CUSTOMERS (USER_ID, CONTACT_NUMBER, ADDRESS_LINE_1, ADDRESS_LINE_2, SUBURB, POSTCODE, STATE, ANNONYMOUS) VALUES ('" + userID + "', '" + contactNumber + "', '" + addressLine1 + "', '" + addressLine2 + "', '" + suburb + "', '" + postCode +"', '" + state + "', 'false')";
      
      // Execute the above query
      st.executeUpdate(insertToCustomer);

      // Build the insertion statement to insert the record into PAYMENT_INFORMATION
      String insertToPaymentInformation = "INSERT INTO PAYMENT_INFORMATION (USER_ID, CARD_NUMBER, CVV_NUMBER, EXPIRY_MONTH, EXPIRY_YEAR) VALUES ('" + userID + "', '" + cardNumber + "', '" + cvv + "', '" + expiryMonth + "', '" + expiryYear + "')";
      st.executeUpdate(insertToPaymentInformation);

      // Return the built Customer object
      Customer cus = new Customer (
         firstName, lastName, password, email, contactNumber,
         addressLine1, addressLine2, suburb, postCode, state,
         cardNumber, cvv, expiryMonth, expiryYear
      );

      return cus;
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
      for (String string : collection) {
         if (string.equals(value)) {
            return false;
         }
      }

      return true;
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