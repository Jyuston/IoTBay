package uts.isd.controller;

import uts.isd.model.dao.DBConnector;

import javax.servlet.http.HttpServlet;
import java.sql.SQLException;

public class ConnServlet extends HttpServlet {
    /**
     * Open a singleton db connection on application deploy.
     * This connection can be used in DAOs by calling `DBConnector.getConnection()`
     */
    @Override
    public void init() {
        try {
            DBConnector.openConnection();
        } catch (ClassNotFoundException | SQLException err) {
            err.printStackTrace();
        }
    }

    /**
     * Close the singleton db connection when application is stopped.
     */
    @Override
    public void destroy() {
        try {
            DBConnector.closeConnection();
        } catch (SQLException err) {
            err.printStackTrace();
        }
    }
}
