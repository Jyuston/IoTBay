package uts.isd.model.dao;

import java.sql.*;

public class DBConnector {
    private static final String URL = "jdbc:derby://localhost:1527/testdb";
    private static final String USER = "TST";
    private static final String PASS = "admin";
    private static final String driver = "org.apache.derby.jdbc.ClientDriver";
    private static Connection dbConnection = null;

    /**
     * Returns the singleton database connection to be used throughout the app.
     * Will create an initial connection if no connection had been made previously.
     *
     * @return Connection object
     */
    public static Connection getConnection() {
        if (dbConnection == null) {
            try {
                Class.forName(driver);
                dbConnection = DriverManager.getConnection(URL, USER, PASS);
            } catch (SQLException | ClassNotFoundException ex) {
                throw new RuntimeException("Error connecting to the database", ex);
            }
        }

        return dbConnection;
    }
}
