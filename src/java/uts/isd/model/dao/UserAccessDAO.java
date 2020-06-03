
package uts.isd.model.dao;

import java.sql.*;
import uts.isd.model.Account;

public class UserAccessDAO {
    
    public static int save(int account_id, String action) throws SQLException, DAOException 
    {
     String query =
                "INSERT INTO USER_ACCESS (ACCOUNT_ID, PERFORMED_ON, ACTION)" +
                "VALUES (?, CURRENT_TIMESTAMP, ?)";
     
     PreparedStatement accessInsertSt = DAOUtils.prepareStatement(
             query, 
             true,
             account_id,
             action             
             );
     
     int rowsChanged = accessInsertSt.executeUpdate();
     if (rowsChanged == 0)
         throw new DAOException("User acces creation failed. Please try again.");
     
     return DAOUtils.getGeneratedID(accessInsertSt);
    }
}
    

