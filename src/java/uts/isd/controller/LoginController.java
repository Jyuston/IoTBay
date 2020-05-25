package uts.isd.controller;

import java.sql.SQLException;
import uts.isd.model.Account;
import uts.isd.model.Customer;
import uts.isd.model.Staff;

import javax.servlet.http.HttpSession;

public class LoginController {
    /**
     * Log in either a Customer or Staff and sets the `user` session attribute
     *
     * @param email Account email address
     * @param password Account password
     * @param session The session to log the user into
     * @return Whether or not the login was successful
     */
    public static boolean login(String email, String password, HttpSession session) {
        // Can do validation here in the controllers
        Character accountType = null;
        try {
            accountType = Account.getAccountType(email, password);
        } catch (SQLException err) {
            err.printStackTrace();
        }

        if (accountType == null) {
            System.err.println("No account found when logging in: " + email);
            return false;
        }

        Account account = (accountType == 'C') ?
                Customer.findByEmailPass(email, password) :
                Staff.findByEmailPass(email, password);

        session.setAttribute("user", account);
        return true;
    }
}
