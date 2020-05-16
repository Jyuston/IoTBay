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

/* 
* DBManager is the primary DAO class to interact with the database. 
* Complete the existing methods of this classes to perform CRUD operations with the db.
*/

public class DBManager {
   private Statement st;

   public DBManager(Connection conn) throws SQLException {
      st = conn.createStatement();
   }

   // Find user by email and password in the database
   public Customer findUser(String email, String password) throws SQLException {
      // setup the select sql query string
      String getUserIdQuery = ("SELECT USER_ID FROM ACCOUNTS WHERE USER_EMAIL LIKE '" + email + "' AND PASSWORD LIKE '" + password + "'");
      // execute this query using the statement field
      ResultSet userIDResult = st.executeQuery(getUserIdQuery);

      // add the results to a ResultSet
      // search the ResultSet for a user using the parameters
      userIDResult.next();
      
      if (userIDResult.getString(1) != null) {
         String getCustomerDetailsQuery = ("SELECT * FROM ACCOUNTS INNER JOIN CUSTOMERS on ACCOUNTS.USER_ID = CUSTOMERS.USER_ID INNER JOIN PAYMENT_INFORMATION on ACCOUNTS.USER_ID = PAYMENT_INFORMATION.USER_ID WHERE ACCOUNTS.USER_ID = '" + userIDResult.getString(1) + "'");
         ResultSet customerDetails = st.executeQuery(getCustomerDetailsQuery);
  
         while (customerDetails.next()) {
            String userEmail = customerDetails.getString(2);
            String firstName = customerDetails.getString(3);
            String lastName = customerDetails.getString(4);
            String userPassword = customerDetails.getString(6);
            String userType = customerDetails.getString(5);
            String contactNumber = customerDetails.getString(8);
            String addressLine1 = customerDetails.getString(9);
            String addressLine2 = customerDetails.getString(10);
            String suburb = customerDetails.getString(11);
            String state = customerDetails.getString(12);
            String postCode = customerDetails.getString(13);
            String cardNumber = customerDetails.getString(15);
            String cvv = customerDetails.getString(17);
            String expiryMonth = customerDetails.getString(18);
            String expiryYear = customerDetails.getString(19);

            Customer cus = new Customer(firstName, lastName, password, userEmail, contactNumber, addressLine1,
                  addressLine2, suburb, postCode, state, cardNumber, cvv, expiryMonth, expiryYear);
            return cus;
         }
      }

      return null;
   }

   // Add a user-data into the database
   public void addUser(String email, String name, String password, String gender, String favcol) throws SQLException { 
      // code
      // for
      // add-operation
      st.executeUpdate("sql query");

   }

   // update a user details in the database
   public void updateUser(String email, String name, String password, String gender, String favcol) throws SQLException {
      // code for update-operation

   }

   // delete a user from the database
   public void deleteUser(String email) throws SQLException {
      // code for delete-operation

   }

}