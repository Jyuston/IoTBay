package uts.isd.model.dao;

import uts.isd.model.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class StaffDAO implements DAO<Staff> {
    private final Connection dbConnection;

    public StaffDAO() {
        dbConnection = DBConnector.getConnection();
    }

    public Staff get(String email, String password) throws SQLException {
        // Setting up the initial SQL query to find the customer by email and password
        Statement st = dbConnection.createStatement();
        String getUserIdQuery =
                "SELECT USER_ID FROM ACCOUNTS " +
                "WHERE USER_EMAIL LIKE '" + email + "' " +
                "AND PASSWORD LIKE '" + password + "'";

        ResultSet userIDRs = st.executeQuery(getUserIdQuery);

        // If no user, return null
        if (!userIDRs.next())
            return null;

        return get(userIDRs.getString("USER_ID"));
    }

    @Override
    public Staff get(String accountID) throws SQLException {
        Statement st = dbConnection.createStatement();
        String getStaffDataQuery =
                "SELECT * FROM ACCOUNTS A " +
                "INNER JOIN STAFF S on A.USER_ID = S.USER_ID " +
                "WHERE A.USER_ID = '" + accountID + "'";

        ResultSet staffRs = st.executeQuery(getStaffDataQuery);

        // If no data, return null Customer
        if (!staffRs.next())
            return null;

        return createStaffObject(staffRs);
    }

    @Override
    public List<Staff> getAll() throws SQLException {
        LinkedList<Staff> customers = new LinkedList<>();

        Statement st = dbConnection.createStatement();
        String getUserIdQuery =
                "SELECT * FROM ACCOUNTS A " +
                "INNER JOIN STAFF S on A.USER_ID = S.USER_ID";

        ResultSet customersRs = st.executeQuery(getUserIdQuery);

        while (customersRs.next()) {
            customers.add(createStaffObject(customersRs));
        }

        return customers;
    }

    @Override
    public void save(Staff staff) throws SQLException {
        Statement st = dbConnection.createStatement();

        String accountInsertQuery = String.format(
                "INSERT INTO ACCOUNTS (USER_ID, USER_EMAIL, USER_F_NAME, USER_L_NAME, PASSWORD, ACCOUNT_TYPE) " +
                "VALUES ('%s', '%s', '%s', '%s', '%s', '%s')",
                staff.getID(),
                staff.getEmail(),
                staff.getFirstName(),
                staff.getLastName(),
                staff.getPassword(),
                "S"
        );
        st.executeQuery(accountInsertQuery);

        String paymentInfoInsertQuery = String.format(
                "INSERT INTO STAFF (USER_ID, IS_ADMIN) " +
                "VALUES ('%s', '%s')",
                staff.getID(),
                staff.isAdmin()
        );
        st.executeQuery(paymentInfoInsertQuery);
    }

    @Override
    public void update(Staff customer, String[] params) {
    }

    @Override
    public void delete(Staff customer) throws SQLException {
    }

    // Helpers
    private Staff createStaffObject(ResultSet customerRs) throws SQLException {
        return new Staff(
                customerRs.getString("USER_ID"),
                customerRs.getString("USER_F_NAME"),
                customerRs.getString("USER_L_NAME"),
                customerRs.getString("USER_EMAIL"),
                customerRs.getString("PASSWORD"), // Lol
                customerRs.getBoolean("IS_ADMIN")
        );
    }
}
