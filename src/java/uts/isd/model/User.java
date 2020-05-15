/*
 * User is a JavaBean that stores details about the user.
 */
package uts.isd.model;

import com.sun.xml.registry.uddi.bindings_v2_2.Contact;
import java.io.Serializable;

/**
 *
 * @author denni
 */
public class User implements Serializable {
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String userType;
    
    // Constructor

    public User(String firstName, String lastName, String password, String email, String userType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.userType = userType;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
    
    public void printNames() {
        System.out.println("First name: " + firstName);
        System.out.println("Last name: " + lastName);
        System.out.println("Email: " + email);
        System.out.println("User type: " + userType);
    }
}
