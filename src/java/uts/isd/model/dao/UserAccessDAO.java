
package uts.isd.model.dao;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import uts.isd.model.Account;
import uts.isd.model.Log;

public class UserAccessDAO {
    
    public static List<Log> getLogs(int accountID) throws SQLException {
        LinkedList<Log> logs = new LinkedList<>();
        
        String getUserLogs = 
                "SELECT * FROM USER_ACCESS WHERE (ACCOUNT_ID = ?)";
        
        PreparedStatement getUserLog = DAOUtils.prepareStatement(
                getUserLogs, 
                true, 
                accountID);
        ResultSet logRs = getUserLog.executeQuery();
        
        while (logRs.next()) {
            logs.add(createLogsObject(logRs));
        }
        return logs;
       
        }
    
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
    
        //Helper
    private static Log createLogsObject(ResultSet logRs) throws SQLException {
        Log logs = new Log();
        logs.setID(logRs.getInt("ACCOUNT_ID"));
        logs.setPerformedOn(logRs.getString("PERFORMED_ON"));
        logs.setAction(logRs.getString("ACTION"));
        return logs;
        
    }
    }