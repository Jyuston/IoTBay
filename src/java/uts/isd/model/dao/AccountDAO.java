package uts.isd.model.dao;

import uts.isd.model.Account;

import java.sql.*;

public class AccountDAO {
    private static final Connection dbConnection = DBConnector.getConnection();
    
    public static Character getAccountType(String email, String password) throws SQLException {
        Statement st = dbConnection.createStatement();
        String accountTypeQuery =
            "SELECT ACCOUNT_TYPE FROM ACCOUNTS " +
            "WHERE EMAIL LIKE '" + email + "' " +
            "AND PASSWORD LIKE '" + password + "'"; 
        
        ResultSet accountTypeRs = st.executeQuery(accountTypeQuery);

        if (!accountTypeRs.next())
           return null;
        
        return accountTypeRs.getString("ACCOUNT_TYPE").charAt(0);
    }

    /**
     * @return Auto-generated ID of new Account
     */
    public static Integer save(Account account) throws SQLException {
        PreparedStatement accountInsertSt = dbConnection.prepareStatement(
                "INSERT INTO ACCOUNTS (EMAIL, F_NAME, L_NAME, CONTACT_NUMBER, ACCOUNT_TYPE, PASSWORD, IS_ACTIVE) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
        );
        accountInsertSt.setString(1, account.getEmail());
        accountInsertSt.setString(2, account.getFirstName());
        accountInsertSt.setString(3, account.getLastName());
        accountInsertSt.setString(4, account.getContactNumber());
        accountInsertSt.setString(5, String.valueOf(account.getAccountType()));
        accountInsertSt.setString(6, account.getPassword());
        accountInsertSt.setBoolean(7, account.isActive());
        accountInsertSt.executeUpdate();

        // Return Auto ID that was generated by SQL Server
        ResultSet generatedKeysRs = accountInsertSt.getGeneratedKeys();
        generatedKeysRs.next();
        return generatedKeysRs.getInt(1);
    } 
    
    public static Account getAccount(String firstName, String lastName, String contactNumber) throws SQLException{
        Statement st = dbConnection.createStatement();
        String getAccountDataQuery = 
                "SELECT ID, ACCOUNT_TYPE FROM ACCOUNTS " +
                "WHERE F_NAME LIKE '" + firstName + "' " +
                "AND L_NAME LIKE '" + lastName + "' " + 
                "AND CONTACT_NUMBER LIKE '" + contactNumber + "'";
        
        ResultSet accountRs = st.executeQuery(getAccountDataQuery);
        if (!accountRs.next()) return null;
        
        if ((accountRs.getString("ACCOUNT_TYPE").charAt(0)) == 'C'){
            System.out.print(accountRs.getString("ID"));
            return CustomerDAO.get(accountRs.getInt("ID"));
        } 
        else if ((accountRs.getString("ACCOUNT_TYPE").charAt(0)) == 'S'){
            return StaffDAO.get(accountRs.getString("ID"));
        }
        
        return null;
    }
}
