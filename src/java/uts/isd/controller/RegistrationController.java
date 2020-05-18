package uts.isd.controller;

import java.sql.*;
import java.util.logging.*;

import uts.isd.model.Address;
import uts.isd.model.Customer;
import uts.isd.model.PaymentInformation;
import uts.isd.model.dao.*;

public class RegistrationController {
    public static Customer registerCustomer(String firstName, String lastName, String email, String password, String contactNumber, Address address, PaymentInformation paymentInfo) {
        try {
            // isn't this opening a new connection every time we register a customer?
            DBConnector connector = new DBConnector();
            Connection conn = connector.openConnection();
            DBManager db = new DBManager(conn);

            Customer customer = db.addCustomer(firstName, lastName, email, password, contactNumber, address, paymentInfo);
            
            connector.closeConnection();
            
            return customer;
        } 

        catch (Exception ex) {
            Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
 
}