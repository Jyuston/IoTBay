package uts.isd.model;

import java.io.Serializable;

public class Address implements Serializable {
    private String addressLine1;
    private String addressLine2;
    private String suburb;
    private String postcode;
    private String state;

    public Address() { }

    public String getAddressLine1() { return addressLine1; }
    public void setAddressLine1(String addressLine1) { this.addressLine1 = addressLine1; }

    public String getAddressLine2() { return addressLine2; }
    public void setAddressLine2(String addressLine2) { this.addressLine2 = addressLine2; }

    public String getSuburb() { return suburb; }
    public void setSuburb(String suburb) { this.suburb = suburb; }

    public String getPostcode() { return postcode; }
    public void setPostcode(String postcode) { this.postcode = postcode; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String dbStringFormat() {
        return String.format("%s-%s-%s-%s-%s", addressLine1, addressLine2, suburb, postcode, state);
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %s %s", addressLine1, addressLine2, suburb, postcode, state);
    }
}
