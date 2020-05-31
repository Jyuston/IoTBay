package uts.isd.model.dao;

import uts.isd.model.Staff;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class StaffDAO {
    public static Staff get(String email, String password) throws SQLException {
        String getStaffQuery =
                "SELECT * FROM ACCOUNTS A " +
                "INNER JOIN STAFF S on A.ID = S.ID " +
                "WHERE EMAIL LIKE ? AND PASSWORD LIKE ?";

        PreparedStatement st = DAOUtils.prepareStatement(getStaffQuery, false, email, password);
        ResultSet staffRs = st.executeQuery(getStaffQuery);

        // If no table rows, return null
        if (!staffRs.next())
            return null;

        return createStaffObject(staffRs);
    }

    public static Staff get(String accountID) throws SQLException {
        String getStaffDataQuery =
                "SELECT * FROM ACCOUNTS A " +
                "INNER JOIN STAFF S on A.ID = S.ID " +
                "WHERE A.ID = ?";

        PreparedStatement st = DAOUtils.prepareStatement(getStaffDataQuery, false, accountID);
        ResultSet staffRs = st.executeQuery(getStaffDataQuery);

        // If no table rows, return null
        if (!staffRs.next())
            return null;

        return createStaffObject(staffRs);
    }

    public static List<Staff> getAll() throws SQLException {
        LinkedList<Staff> customers = new LinkedList<>();

        String getUserIdQuery =
                "SELECT * FROM ACCOUNTS A " +
                "INNER JOIN STAFF S on A.ID = S.ID";

        PreparedStatement st = DAOUtils.prepareStatement(getUserIdQuery, false);
        ResultSet customersRs = st.executeQuery(getUserIdQuery);

        while (customersRs.next()) {
            customers.add(createStaffObject(customersRs));
        }

        return customers;
    }

    public static void save(Staff staff) throws SQLException {
        int newID = AccountDAO.save(staff);

        String staffInsertQuery = "INSERT INTO STAFF (ID, IS_ADMIN) VALUES (?, ?)";
        PreparedStatement staffInsertSt = DAOUtils.prepareStatement(staffInsertQuery, false, newID, staff.isAdmin());

        int rowsChanged = staffInsertSt.executeUpdate();
        if (rowsChanged == 0)
            throw new DAOException("Failed to create staff profile. Please try again.");
    }

    public static void update(Staff customer, String[] params) {
    }

    public static void delete(Staff customer) {
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
