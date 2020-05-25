package uts.isd.model;

import uts.isd.model.dao.AccountDAO;
import uts.isd.model.dao.StaffDAO;

import java.sql.SQLException;

public class Staff extends Account {
    private boolean isAdmin;

    private static final StaffDAO DAO = new StaffDAO();

    public Staff(String ID, String firstName, String lastName, String email, String password, boolean isAdmin) {
        super(ID, firstName, lastName, email, password);
        this.isAdmin = isAdmin;
    }

    public boolean isAdmin() { return isAdmin; }
    public void setAdmin(boolean admin) { isAdmin = admin; }

    public static Staff create(String firstName, String lastName, String email, String password, boolean isAdmin) {
        String ID;
        try {
            ID = AccountDAO.getNextAvailableID();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        Staff createdCustomer = new Staff(
                ID,
                firstName,
                lastName,
                email,
                password,
                isAdmin
        );

        try {
            DAO.save(createdCustomer);
        } catch (SQLException err) {
            err.printStackTrace();
            return null;
        }

        return createdCustomer;
    }

    public static Staff findByEmailPass(String email, String password) {
        try {
            return DAO.get(email, password);
        } catch (SQLException err) {
            err.printStackTrace();
            return null;
        }
    }
}
