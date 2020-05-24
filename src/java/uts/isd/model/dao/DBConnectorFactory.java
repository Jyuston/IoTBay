package uts.isd.model.dao;


import java.sql.*;

public class DBConnectorFactory {
    public static final String URL = "jdbc:derby://localhost:1527/testdb";
    public static final String USER = "TST";
    public static final String PASS = "admin";
    public static final String driver = "org.apache.derby.jdbc.ClientDriver";

    /**
     * Get a connection to database
     *
     * @return Connection object
     */
    public static Connection getConnection() {
        try {
            Class.forName(driver);
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        }
    }

    /**
     * Test Connection
     */
    public static void main(String[] args) {
        Connection connection = DBConnectorFactory.getConnection();
        System.out.println(connection);
    }
}
