package uts.isd.controller;

import uts.isd.model.Customer;

public class LoginController {
    public static Customer login(String email, String password) {
        // Can do validation here in the controllers

        return Customer.findUserByEmailPass(email, password);
    }

}