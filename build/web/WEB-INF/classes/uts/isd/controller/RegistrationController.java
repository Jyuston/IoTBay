/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.controller;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import uts.isd.model.Customer;
import uts.isd.model.dao.*;

/**
 *
 * @author denni
 */

public class RegistrationController {
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String contactNumber;
    private String addressLine1;
    private String addressLine2;
    private String suburb;
    private String postCode;
    private String state;
    private String cardNumber;
    private String cvv;
    private String expiryMonth;
    private String expiryYear;
    
    
    public RegistrationController (String firstName, String lastName, String password, String email, String contactNumber, String addressLine1, String addressLine2, String suburb, String postCode, String state, String cardNumber, String cvv, String expiryMonth, String expiryYear) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.contactNumber = contactNumber;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.suburb = suburb;
        this.postCode = postCode;
        this.state = state;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expiryMonth = expiryMonth;
        this.expiryYear = expiryYear;
    }
    
    public Customer RegisterCustomer() {
        try {

            DBConnector connector = new DBConnector();

            Connection conn = connector.openConnection();

            DBManager db = new DBManager(conn);

            Customer customer = db.addCustomer(
                firstName, lastName, password, email, contactNumber,
                addressLine1, addressLine2, suburb, postCode, state,
                cardNumber, cvv, expiryMonth, expiryYear
            );

            connector.closeConnection();
            
            return customer;
        } 

        catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
 
}