
package uts.isd.model.dao;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import uts.isd.model.Log;
import java.sql.Timestamp;

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
    
    public static List<Log> getDate(int account_id, String date) throws SQLException
    {
        LinkedList<Log> logs = new LinkedList<>();
        
        Timestamp startDate = Timestamp.valueOf(date+ " 00:00:00");
        Timestamp endDate = Timestamp.valueOf(date + " 23:59:59");
        
        String dateTimeQuery = 
                "SELECT * "
                + "FROM USER_ACCESS "
                + "WHERE ACCOUNT_ID = ? "
                + "AND PERFORMED_ON >= '" 
                + startDate.toString()
                + "' AND PERFORMED_ON < '"
                + endDate.toString()
                +"'"
                ; 
        
        PreparedStatement getDate = DAOUtils.prepareStatement(
                dateTimeQuery,
                false,
                account_id 
        );
        
        ResultSet dateRs = getDate.executeQuery();
        
        while (dateRs.next()) {
            logs.add(createLogsObject(dateRs));
        }
        
        return logs;
        
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
