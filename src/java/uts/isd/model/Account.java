package uts.isd.model;

import java.io.Serializable;

public abstract class Account implements Serializable {
    private String ID;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String contactNumber;
    private boolean active;

    Account(String ID, String firstName, String lastName, String email, String password, String contactNumber, boolean active) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.contactNumber = contactNumber;
        this.active = active;
    }

    public String getID() { return ID; }
    public void setID(String ID) { this.ID = ID; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    // Used in sub classes
    public boolean isStaff() {
        return this.getClass().getSimpleName().charAt(0) == 'S';
    }

    public boolean isCustomer() {
        return this.getClass().getSimpleName().charAt(0) == 'C';
    }
}
