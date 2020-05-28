package uts.isd.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import uts.isd.model.Account;
import uts.isd.model.dao.AccountDAO;
import uts.isd.model.dao.CustomerDAO;
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

            // If no account found, set error message on request
            // TODO change to proper Exception. NullPointer is not used in this scenario
            if (accountType == null) throw new NullPointerException("Incorrect Username or Password.");

            Account account = (accountType == 'C') ?
                    CustomerDAO.get(email, password) :
                    StaffDAO.get(email, password);

            // Same as above
            if (account == null) throw new NullPointerException("Incorrect Username or Password.");

            request.getSession().setAttribute("user", account);
        } catch (NullPointerException err) {
            request.setAttribute("errorLogin", err.getMessage());
        } catch (SQLException err) {
            request.setAttribute("errorLogin", "Error accessing database.");
            err.printStackTrace();
        } finally {
            request.getRequestDispatcher("/login.jsp").include(request, response);
        }
    }
}
