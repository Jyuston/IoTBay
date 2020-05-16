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

public class LoginController {
    private static Scanner in = new Scanner(System.in);
    private String email;
    private String password;
    
    public LoginController(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    public Customer StartLogin() {
        try {

            DBConnector connector = new DBConnector();

            Connection conn = connector.openConnection();

            DBManager db = new DBManager(conn);

            Customer customer = db.findUser(email, password);

            connector.closeConnection();
            
            return customer;

        } 

        catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
 
}