package uts.isd.controller;

import uts.isd.model.dao.DBConnector;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnServlet extends HttpServlet {
    private final DBConnector connector = new DBConnector();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("connecting");
        try {
            Connection dbConnection = connector.getConnection();
            request.getSession().setAttribute("dbConnection", dbConnection);
        } catch (ClassNotFoundException | SQLException err) {
            err.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        try {
            connector.closeConnection();
        } catch (SQLException err) {
            err.printStackTrace();
        }
    }
}
