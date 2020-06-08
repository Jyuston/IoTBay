package uts.isd.controller;

import uts.isd.model.Product;

import javax.servlet.http.HttpServletRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private final String emailPattern = "([a-zA-Z0-9]+)(([._-])([a-zA-Z0-9]+))*(@)([a-z]+)(.)([a-z]{3})((([.])[a-z]{0,2})*)";
    private final String namePattern = "([A-Z][a-z]+[\\s])+[A-Z][a-z]*";
    private final String passwordPattern = "[a-z0-9]{4,}";
    private final String contactNumberPattern = "[0-9]{4} [0-9]{3} [0-9]{3}";
    
    private final String addressPattern = "^\\d+\\s[A-z]+\\s[A-z]+";
    private final String addressPattern2 = "^$|^\\d+\\s[A-z]+\\s[A-z]+";
    private final String suburbPattern = "^[A-Z][a-z]+";
    private final String postcodePattern = "^[0-9]{4}";
    
    private final String cardNumberPattern = "[0-9]{4} [0-9]{4} [0-9]{4} [0-9]{4}";
    private final String cvvPattern = "[0-9]{3}";
    private final String expiryMonthPattern = "[0-9]{2}";
    private final String expiryYearPattern = "[0-9]{4}";
    
    private final HttpServletRequest request;
    private boolean failed = false;

    public Validator(HttpServletRequest request) {
        this.request = request;
    }

    public boolean failed() { return failed; }

    public Validator checkEmpty(String input, String errorPrefix) {
        if (input.isEmpty()) {
            request.setAttribute("empty" + errorPrefix + "VErr", errorPrefix + " field cannot be empty");
            failed = true;
        }

        return this;
    }

    public Validator checkEmptyEmailPass(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            request.setAttribute("emptyEmailPassVErr", "Email or Password field cannot be empty");
            failed = true;
        }

        return this;
    }

    public Validator validateEmail(String email) {
        if (!validate(emailPattern, email)) {
            request.setAttribute("emailVErr", "Email format incorrect");
            failed = true;
        }

        return this;
    }


    public Validator validateName(String name) {
        if (!validate(namePattern, name)) {
            request.setAttribute("nameVErr", "Name format incorrect");
            failed = true;
        }

        return this;
    }


    public Validator validatePassword(String password) {
        if (!validate(passwordPattern, password))  {
            request.setAttribute("passVErr", "Password format incorrect. Must be at least 4 characters");
            failed = true;
        }

        return this;
    }
    
    public Validator validateContactNumber(String contactNumber) {
        if (!validate(contactNumberPattern, contactNumber)){
            request.setAttribute("contactNumberVErr", "Contact Number format incorrect. Must be correct length & spaced");
            failed = true;
        }
    
        return this;
    }
    
    // Address
    
    public Validator validateAddress(String address) {
        if (!validate(addressPattern, address)){
            request.setAttribute("addressVErr", "Address format incorrect. Must be number followed by address");
            failed = true;
        }
    
        return this;
    }
    
    public Validator validateAddress2(String address) {
        if (!validate(addressPattern2, address)){
            request.setAttribute("address2VErr", "Address format incorrect. Must be blank or Number followed by Address");
            failed = true;
        }
    
        return this;
    }

    public Validator validateSuburb(String suburb) {
        if (!validate(suburbPattern, suburb)){
            request.setAttribute("suburbVErr", "Suburb format incorrect. Must start with a capital letter");
            failed = true;
        }
    
        return this;
    }

    public Validator validatePostcode(String postcode) {
        if (!validate(postcodePattern, postcode)){
            request.setAttribute("postcodeVErr", "Postcode format incorrect. Must be 4 digits");
            failed = true;
        }
    
        return this;
    }    
    
    //Payment Information

    public Validator validateCardNumber(String cardNumber) {
        if (!validate(cardNumberPattern, cardNumber)){
            request.setAttribute("cardNumberVErr", "Credit Card format incorrect. Must be 4 spaced intervals of 4 digits");
            failed = true;
        }
    
        return this;
    }

    public Validator validateCvv(String cvv) {
        if (!validate(cvvPattern, cvv)){
            request.setAttribute("cvvVErr", "CVV format incorrect. Must be 3 digits");
            failed = true;
        }
    
        return this;
    }

    public Validator validateExpiryMonth(String expiryMonth) {
        if (!validate(expiryMonthPattern, expiryMonth)){
            request.setAttribute("expiryMonthVErr", "Month format incorrect. Must be 2 digits");
            failed = true;
        }
    
        return this;
    }

    public Validator validateExpiryYear(String expiryYear) {
        if (!validate(expiryYearPattern, expiryYear)){
            request.setAttribute("expiryYearVErr", "Year format incorrect. Must be 4 digits");
            failed = true;
        }
        
        return this;
    }

    // Product

    public Validator checkNegativePrice(double price) {
        if (price < 0)  {
            request.setAttribute("negativePriceVErr", "Price cannot be negative");
            failed = true;
        }

        return this;
    }

    public Validator checkNegativeStock(int price) {
        if (price < 0)  {
            request.setAttribute("negativeStockVErr", "Stock cannot be negative");
            failed = true;
        }

        return this;
    }

    public Validator validateCartUpdate(Product product, int quantity) {
        if (quantity > product.getStock()) {
            failed = true;
            request.setAttribute("availableStockVErr", "Can't add to cart. Not enough stock left.");
        }

        return this;
    }

    public Validator validateReportDates(String d1, String d2) throws ParseException, Exception {
        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(d1);
        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(d2);

        if (startDate.compareTo(endDate) > 0) {
            failed = true;
            request.setAttribute("error", "The report end date cannot be before the report start date.");
        }

        return this;
    }

    public Validator validateReportDescription(String description) throws Exception {
        // Note - for reporting, any report description is valid provided it is not empty
        if (description.equals("")) {
            failed = true;
            request.setAttribute("error", "The report description cannot be empty.");
        }

        return this;
    }

    public Validator validateReportName(String name) throws Exception {
        // Note - for reporting, any report name is valid provided it is not empty
        if (name.equals("")) {
            failed = true;
            request.setAttribute("error", "The report name cannot be empty.");
        }

        return this;
    }

    // Helpers

    private boolean validate(String pattern, String input) {
        Pattern regEx = Pattern.compile(pattern);
        Matcher match = regEx.matcher(input);

        return match.matches();
    }
}