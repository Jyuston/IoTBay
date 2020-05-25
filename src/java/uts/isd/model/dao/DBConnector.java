package uts.isd.model.dao;

import java.sql.*;

public class DBConnector {
    private static final String URL = "jdbc:derby://localhost:1527/testdb";
    private static final String USER = "TST";
    private static final String PASS = "admin";
    private static final String driver = "org.apache.derby.jdbc.ClientDriver";
    private static Connection dbConnection = null;

    /**
     * Get the database connection
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
