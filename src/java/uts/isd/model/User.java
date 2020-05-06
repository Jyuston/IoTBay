/*
 * User is a JavaBean that stores details about the user.
 */
package uts.isd.model;

import java.io.Serializable;

/**
 *
 * @author denni
 */
public class User implements Serializable {
    private String firstName;
    private String lastName;
    private String password;
    private String dob;
    private String email;
    private String contactNumber;
    private String userType;
    
    // Constructor

    public User(String firstName, String lastName, String password, String dob, String email, String contactNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.dob = dob;
        this.email = email;
        this.contactNumber = contactNumber;
        this.userType = "Customer";
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
