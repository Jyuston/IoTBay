/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model;

import java.io.Serializable;

/**
 *
 * @author denni
 */
public class PaymentInformation implements Serializable {
    private String cardNumber;
    private String cvv;
    private String expiryMonth;
    private String expiryYear;

    // Constructor
    public PaymentInformation(String cardNumber, String cvv, String expiryMonth, String expiryYear) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expiryMonth = expiryMonth;
        this.expiryYear = expiryYear;
    }

    // Getters and Setters
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvc(String cvc) {
        this.cvv = cvc;
    }

    public String getExpiryMonth() {
        return expiryMonth;
    }

    public void setExpiryMonth(String expiryMonth) {
        this.expiryMonth = expiryMonth;
    }

    public String getExpiryYear() {
        return expiryYear;
    }

    public void setExpiryYear(String expiryYear) {
        this.expiryYear = expiryYear;
    }
    
    public void printPaymentInfo() {
        System.out.println("Card Number: " + cardNumber);
        System.out.println("CVC: " + cvv);
        System.out.println("Expiry Month: " + expiryMonth);
        System.out.println("Expiry Year: " + expiryYear);
    }
}
