package uts.isd.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// Does not implement the DAO interface as no CRUD operations are to be done directly on the Account.
public class AccountDAO {
    private static final Connection dbConnection = DBConnector.getConnection();

    // Will probably change this later. Very basic rn.
    public static String getNextAvailableID() throws SQLException {
        Statement st = dbConnection.createStatement();

        String accountIDsQuery = "SELECT USER_ID FROM ACCOUNTS";
        ResultSet accountIDsRs = st.executeQuery(accountIDsQuery);

        if (!accountIDsRs.last())
            return "U-1";

        String lastID = accountIDsRs.getString("USER_ID");
        int lastNumber = Integer.parseInt(lastID.substring(2));
        return "U-" + (lastNumber + 1);
    }
    
    public static String getAccountType(String email, String password) throws SQLException{
        Statement st = dbConnection.createStatement();
        String getAccTypeQuery =
            "SELECT ACCOUNT_TYPE FROM ACCOUNTS " +
            "WHERE USER_EMAIL LIKE '" + email + "' " +
            "AND PASSWORD LIKE '" + password + "'"; 
        
        ResultSet accountType = st.executeQuery(getAccTypeQuery);
        if (!accountType.next())
           return null;
        
        return accountType.getString("ACCOUNT_TYPE");
    } 
    
}
