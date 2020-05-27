package uts.isd.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountDAO {
    private static final Connection dbConnection = DBConnector.getConnection();

    // THIS SHOULD GET THE LAST USER USING SQL QUERY
    public static String getNextAvailableID() throws SQLException {
        Statement st = dbConnection.createStatement();

        String accountIDsQuery = "SELECT USER_ID FROM ACCOUNTS";
        ResultSet accountIDsRs = st.executeQuery(accountIDsQuery);

        // BROKEN RN
        if (!accountIDsRs.next())
            return "U-1";

        String lastID = accountIDsRs.getString("USER_ID");
        int lastNumber = Integer.parseInt(lastID.substring(2));
        return "U-" + (lastNumber + 1);
    }
    
    public static Character getAccountType(String email, String password) throws SQLException {
        Statement st = dbConnection.createStatement();
        String accountTypeQuery =
            "SELECT ACCOUNT_TYPE FROM ACCOUNTS " +
            "WHERE USER_EMAIL LIKE '" + email + "' " +
            "AND PASSWORD LIKE '" + password + "'"; 
        
        ResultSet accountTypeRs = st.executeQuery(accountTypeQuery);

        if (!accountTypeRs.next())
           return null;
        
        return accountTypeRs.getString("ACCOUNT_TYPE").charAt(0);
    } 
    
}
