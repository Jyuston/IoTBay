package uts.isd.model.dao;

import java.sql.*;

public class DBConnector {
    private final String URL = "jdbc:derby://localhost:1527/testdb";
    private final String USER = "tst";
    private final String PASS = "admin";
    private final String driver = "org.apache.derby.jdbc.ClientDriver";

    private Connection dbConnection;

    /**
     * Used in the ConnServlet to establish a single connection
     * to be used throughout the session.
     *
     * @return Connection to the database
     */
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        dbConnection = DriverManager.getConnection(URL, USER, PASS);

        return dbConnection;
    }

    public void closeConnection() throws SQLException {
        dbConnection.close();
    }
}
