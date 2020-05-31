package uts.isd.model.dao;

import uts.isd.model.Staff;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class StaffDAO {
    public static final Connection dbConnection = DBConnector.getConnection();

    public static Staff get(String email, String password) throws SQLException {
        Statement st = dbConnection.createStatement();
        String getStaffQuery =
                "SELECT * FROM ACCOUNTS A " +
                "INNER JOIN STAFF S on A.ID = S.ID " +
                "WHERE EMAIL LIKE '" + email + "' " +
                "AND PASSWORD LIKE '" + password + "'";

        ResultSet staffRs = st.executeQuery(getStaffQuery);

        // If no table rows, return null
        if (!staffRs.next())
            return null;

        return createStaffObject(staffRs);
    }

    public static Staff get(String accountID) throws SQLException {
        Statement st = dbConnection.createStatement();
        String getStaffDataQuery =
                "SELECT * FROM ACCOUNTS A " +
                "INNER JOIN STAFF S on A.ID = S.ID " +
                "WHERE A.ID = '" + accountID + "'";

        ResultSet staffRs = st.executeQuery(getStaffDataQuery);

        // If no table rows, return null
        if (!staffRs.next())
            return null;

        return createStaffObject(staffRs);
    }

    public static List<Staff> getAll() throws SQLException {
        LinkedList<Staff> customers = new LinkedList<>();

        Statement st = dbConnection.createStatement();
        String getUserIdQuery =
                "SELECT * FROM ACCOUNTS A " +
                "INNER JOIN STAFF S on A.ID = S.ID";

        ResultSet customersRs = st.executeQuery(getUserIdQuery);

        while (customersRs.next()) {
            customers.add(createStaffObject(customersRs));
        }

        return customers;
    }

    public static void save(Staff staff) throws SQLException {
        int newID = AccountDAO.save(staff);

        PreparedStatement staffInsertSt = dbConnection.prepareStatement(
                "INSERT INTO STAFF (ID, IS_ADMIN) " +
                "VALUES (?, ?)"
        );
        staffInsertSt.setInt(1, newID);
        staffInsertSt.setBoolean(2, staff.isAdmin());
        staffInsertSt.executeUpdate();
    }

    public static void update(Staff customer, String[] params) {
    }

    public static void delete(Staff customer) throws SQLException {
    }

    // Helpers
    private static Staff createStaffObject(ResultSet staffRs) throws SQLException {
        Staff staff = new Staff();

        staff.setAdmin(staffRs.getBoolean("IS_ADMIN"));

        return staff;
    }
}
