package uts.isd.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import uts.isd.model.Account;
import uts.isd.model.dao.AccountDAO;
import uts.isd.model.dao.CustomerDAO;
import uts.isd.model.dao.DAOException;
import uts.isd.model.dao.StaffDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Get form details
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Try to log user in
        try {
            Character accountType = AccountDAO.getAccountType(email, password);

            Account account = (accountType == 'C') ?
                    CustomerDAO.get(email, password) :
                    StaffDAO.get(email, password);

            request.getSession().setAttribute("user", account);
        }
        catch (DAOException err) {
            request.setAttribute("errorLogin", err.getMessage());
        }
        catch (SQLException err) {
            request.setAttribute("errorLogin", "Error accessing database.");
            err.printStackTrace();
        }
        finally {
            request.getRequestDispatcher("/login.jsp").include(request, response);
        }
    }
}
