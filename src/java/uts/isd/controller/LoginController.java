package uts.isd.controller;

import java.sql.SQLException;
import uts.isd.model.Account;
import uts.isd.model.Customer;
import uts.isd.model.Staff;

public class LoginController {
    public static Account login(String email, String password) throws SQLException {
        // Can do validation here in the controllers
         String accountType = Account.getAccountType(email, password);

        if (accountType.equals("C")){
            return Customer.findByEmailPass(email, password);
        } else if (accountType.equals("S")){
            return Staff.findByEmailPass(email, password);
        }
       return null;
    }
}
