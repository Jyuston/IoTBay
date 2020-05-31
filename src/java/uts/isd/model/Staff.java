package uts.isd.model;

public class Staff extends Account {
    private boolean admin;

    public Staff() { }

    public boolean isAdmin() { return admin; }
    public void setAdmin(boolean admin) { this.admin = admin; }
}
