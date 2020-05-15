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

public class TestDB {
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        try {

            DBConnector connector = new DBConnector();

            Connection conn = connector.openConnection();

            DBManager db = new DBManager(conn);

            System.out.print("User email: ");

            String email = in.nextLine();

            System.out.print("User password: ");

            String password = in.nextLine();

            Customer customer = db.findUser(email, password);

            if (customer != null) {
                customer.printCustomer();
            }

            else {
                System.out.println("User not found");
            }

            connector.closeConnection();

        } 

        catch (ClassNotFoundException | SQLException ex) {

            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    
}
