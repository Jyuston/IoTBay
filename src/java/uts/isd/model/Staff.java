package uts.isd.model;

public class Staff extends Account {
    private boolean admin;

    public Staff(Integer ID, String firstName, String lastName, String email, String password, String contactNumber, boolean active, boolean admin) {
        super(ID, firstName, lastName, email, password, contactNumber, active);
        this.admin = admin;
    }

    public boolean isAdmin() { return admin; }
    public void setAdmin(boolean admin) { this.admin = admin; }
}
