package uts.isd.controller;

import java.sql.*;
import java.util.logging.*;
import uts.isd.model.Customer;
import uts.isd.model.dao.*;

public class LoginController {
    public static Customer login(String email, String password) {
        try {
            DBConnector connector = new DBConnector();
            Connection conn = connector.openConnection();
            DBManager db = new DBManager(conn);

            Customer customer = db.findUserByEmailPass(email, password);

            connector.closeConnection();
            
            return customer;
        } 

        catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
 
}