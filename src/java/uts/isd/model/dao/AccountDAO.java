/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import uts.isd.model.Account;

/**
 *
 * @author justinhyuen
 */

//Does not implement the DAO interface as no CRUD operations are to be done directly on the Account.
public class AccountDAO{
    private final Connection dbConnection;
    
    public AccountDAO() {
        dbConnection = DBConnector.getConnection();
    }
// Will probably change this later. Very basic rn.
    public  String getNextAvailableID() throws SQLException {
        Statement st = dbConnection.createStatement();

        String accountIDsQuery = "SELECT USER_ID FROM ACCOUNTS";
        ResultSet accountIDsRs = st.executeQuery(accountIDsQuery);

        if (!accountIDsRs.last())
            return "U-1";

        String lastID = accountIDsRs.getString("USER_ID");
        int lastNumber = Integer.parseInt(lastID.substring(2));
        return "U-" + (lastNumber + 1);
    }
    
    public String getAccountType(String email, String password) throws SQLException{
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
