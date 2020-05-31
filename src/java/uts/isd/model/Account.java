package uts.isd.model;

import java.io.Serializable;

public abstract class Account implements Serializable {
    private int ID;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String contactNumber;
    private boolean active;

    public Account() { }

    public int getID() { return ID; }
    public void setID(int ID) { this.ID = ID; }

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

    public char getAccountType() { return this.getClass().getSimpleName().charAt(0); }

    // Used in sub classes
    public boolean isStaff() { return getAccountType() == 'S'; }
    public boolean isCustomer() { return getAccountType() == 'C'; }
}
