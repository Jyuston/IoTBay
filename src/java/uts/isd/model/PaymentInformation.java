package uts.isd.model;

import java.io.Serializable;

public class PaymentInformation implements Serializable {
    private String cardNumber;
    private String cvvNumber;
    private String expiryMonth;
    private String expiryYear;

    public PaymentInformation() { }

    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

    public String getCvvNumber() { return cvvNumber; }
    public void setCvvNumber(String cvvNumber) { this.cvvNumber = cvvNumber; }

    public String getExpiryMonth() { return expiryMonth; }
    public void setExpiryMonth(String expiryMonth) { this.expiryMonth = expiryMonth; }

    public String getExpiryYear() { return expiryYear; }
    public void setExpiryYear(String expiryYear) { this.expiryYear = expiryYear; }
}
