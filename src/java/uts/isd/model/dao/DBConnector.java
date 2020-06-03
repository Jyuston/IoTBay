package uts.isd.model.dao;

import java.sql.*;

public class DBConnector {
    private static final String URL = "jdbc:derby://localhost:1527/testdb";
    private static final String USER = "tst";
    private static final String PASS = "admin";
    private static final String driver = "org.apache.derby.jdbc.ClientDriver";
    private static Connection dbConnection = null;

    public static void openConnection() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        dbConnection = DriverManager.getConnection(URL, USER, PASS);
    }

    /**
     * Returns the singleton database connection to be used throughout the app.
     * Will create an initial connection if no connection had been made previously.
     *
     * @return Connection object
     */
    public static Connection getConnection() {
        return dbConnection;
    }

    public static void closeConnection() throws SQLException {
        dbConnection.close();
    }
    
     public static void main(String[] args) throws SQLException, ClassNotFoundException {
        openConnection();
        System.out.println(getConnection());
    }
}
