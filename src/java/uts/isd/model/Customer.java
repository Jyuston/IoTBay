/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model;

import java.io.Serializable;
import uts.isd.model.PaymentInformation;

/**
 *
 * @author denni
 */

public class Customer extends User implements Serializable {
    private String addressLine1;
    private String addressLine2;
    private String suburb;
    private String postCode;
    private String state;
    private PaymentInformation customerPaymentInfo;

    // Constructor
    public Customer(String firstName, String lastName, String password, String dob, String email, String contactNumber, String addressLine1, String addressLine2, String suburb, String postCode, String state, String cardNumber, String cvc, String expiryMonth, String expiryYear) {
        super(firstName, lastName, password, dob, email, contactNumber);
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.suburb = suburb;
        this.postCode = postCode;
        this.state = state;
        this.customerPaymentInfo = new PaymentInformation(cardNumber, cvc, expiryMonth, expiryYear);
    }
    
    // Getters and Setters
    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    
    public PaymentInformation getCustomerPaymentInfo() {
        return customerPaymentInfo;
    }

    public void setCustomerPaymentInfo(PaymentInformation customerPaymentInfo) {
        this.customerPaymentInfo = customerPaymentInfo;
    }
    
}