package uts.isd.model.dao;

import uts.isd.model.Account;

import java.sql.*;


public class AccountDAO {
    public static Character getAccountType(String email, String password) throws SQLException, DAOException {
        String accountTypeQuery =
                "SELECT ACCOUNT_TYPE FROM ACCOUNTS " +
                "WHERE EMAIL LIKE ? AND PASSWORD LIKE ?";

        PreparedStatement accountTypeSt = DAOUtils.prepareStatement(accountTypeQuery, false,
                email, password
        );

        ResultSet accountTypeRs = accountTypeSt.executeQuery();

        if (!accountTypeRs.next())
            throw new DAOException("No Account found. Incorrect Email or Password.");

        return accountTypeRs.getString("ACCOUNT_TYPE").charAt(0);
    }

    /**
     * @return Auto-generated ID of new Account
     */
    public static Integer save(Account account) throws SQLException, DAOException {
        String query =
                "INSERT INTO ACCOUNTS (EMAIL, F_NAME, L_NAME, CONTACT_NUMBER, ACCOUNT_TYPE, PASSWORD, IS_ACTIVE) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement accountInsertSt = DAOUtils.prepareStatement(query, true,
                account.getEmail(),
                account.getFirstName(),
                account.getLastName(),
                account.getContactNumber(),
                String.valueOf(account.getAccountType()),
                account.getPassword(),
                account.isActive()
        );

        int rowsChanged = accountInsertSt.executeUpdate();
        if (rowsChanged == 0)
            throw new DAOException("Account creation failed. Please try again.");

        // Return Auto ID that was generated by SQL Server
        return DAOUtils.getGeneratedID(accountInsertSt);
    }
}
