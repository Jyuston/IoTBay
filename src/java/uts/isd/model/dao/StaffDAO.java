package uts.isd.model.dao;

import uts.isd.model.Staff;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class StaffDAO {
    public static Staff get(String email, String password) throws SQLException, DAOException {
        String getStaffQuery =
                "SELECT * FROM ACCOUNTS A " +
                "INNER JOIN STAFF S on A.ID = S.ID " +
                "WHERE EMAIL LIKE ? AND PASSWORD LIKE ?";

        PreparedStatement st = DAOUtils.prepareStatement(getStaffQuery, false, email, password);
        ResultSet staffRs = st.executeQuery();

        if (!staffRs.next())
            throw new DAOException("No Staff found. Incorrect Email or Password.");

        return createStaffObject(staffRs);
    }

    public static Staff get(int accountID) throws SQLException, DAOException {
        String getStaffDataQuery =
                "SELECT * FROM ACCOUNTS A " +
                "INNER JOIN STAFF S on A.ID = S.ID " +
                "WHERE A.ID = ?";

        PreparedStatement st = DAOUtils.prepareStatement(getStaffDataQuery, false, accountID);
        ResultSet staffRs = st.executeQuery();

        if (!staffRs.next())
            throw new DAOException("No Staff with that ID exists.");

        return createStaffObject(staffRs);
    }

    public static List<Staff> getAll() throws SQLException {
        LinkedList<Staff> customers = new LinkedList<>();

        String getUserIdQuery =
                "SELECT * FROM ACCOUNTS A " +
                "INNER JOIN STAFF S on A.ID = S.ID";

        PreparedStatement st = DAOUtils.prepareStatement(getUserIdQuery, false);
        ResultSet customersRs = st.executeQuery();

        while (customersRs.next()) {
            customers.add(createStaffObject(customersRs));
        }

        return customers;
    }

    public static void save(Staff staff) throws SQLException, DAOException {
        int newID = AccountDAO.save(staff);

        String staffInsertQuery = "INSERT INTO STAFF (ID, IS_ADMIN) VALUES (?, ?)";
        PreparedStatement staffInsertSt = DAOUtils.prepareStatement(staffInsertQuery, false, newID, staff.isAdmin());

        int rowsChanged = staffInsertSt.executeUpdate();
        if (rowsChanged == 0)
            throw new DAOException("Failed to create staff profile. Please try again.");
    }

    public static void update(Staff customer, String[] params) {
    }

    // Helpers
    private static Staff createStaffObject(ResultSet staffRs) throws SQLException {
        Staff staff = new Staff();
        staff.setID(staffRs.getInt("ID"));
        staff.setFirstName(staffRs.getString("F_NAME"));
        staff.setLastName(staffRs.getString("L_NAME"));
        staff.setEmail(staffRs.getString("EMAIL"));
        staff.setPassword(staffRs.getString("PASSWORD"));
        staff.setContactNumber(staffRs.getString("CONTACT_NUMBER"));
        staff.setActive(staffRs.getBoolean("IS_ACTIVE"));
        staff.setAdmin(staffRs.getBoolean("IS_ADMIN"));

        return staff;
    }
}
