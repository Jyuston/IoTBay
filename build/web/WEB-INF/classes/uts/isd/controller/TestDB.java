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

            System.out.print("fn: ");

            String firstName = in.nextLine();

            System.out.print("ln: ");

            String lastName = in.nextLine();

            System.out.print("pw: ");

            String password = in.nextLine();

            System.out.print("em: ");

            String email = in.nextLine();

            System.out.print("cn: ");

            String contactNumber = in.nextLine();

            System.out.print("a1: ");

            String addressLine1 = in.nextLine();

            System.out.print("a2: ");

            String addressLine2 = in.nextLine();

            System.out.print("sb: ");

            String suburb = in.nextLine();

            System.out.print("pc: ");

            String postCode = in.nextLine();

            System.out.print("st: ");

            String state = in.nextLine();

            System.out.print("cardn: ");

            String cardNumber = in.nextLine();

            System.out.print("cvv: ");

            String cvv = in.nextLine();

            System.out.print("em: ");

            String expiryMonth = in.nextLine();

            System.out.print("ey: ");

            String expiryYear = in.nextLine();

            db.addCustomer(firstName, lastName, password, email, contactNumber, addressLine1, addressLine2, suburb, postCode, state, cardNumber, cvv, expiryMonth, expiryYear);

            connector.closeConnection();

        } 

        catch (ClassNotFoundException | SQLException ex) {

            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    
}
