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
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Get db connection from session
        HttpSession session = request.getSession();
        Connection dbConnection = (Connection) session.getAttribute("dbConnection");

        // Create DAOs to use in Controller
        AccountDAO accountDAO = new AccountDAO(dbConnection);
        CustomerDAO customerDAO = new CustomerDAO(dbConnection);
        StaffDAO staffDAO = new StaffDAO(dbConnection);

        // Get form details
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Try to log user in
        // If any DAO method returns null, the NullPointerException catch will trigger
        try {
            char accountType = accountDAO.getAccountType(email, password);

            Account account = (accountType == 'C') ?
                    customerDAO.get(email, password) :
                    staffDAO.get(email, password);

            session.setAttribute("user", account);
        } catch (NullPointerException err) {
            request.setAttribute("errorLogin", "Incorrect Username/Password.");
        } catch (SQLException err) {
            request.setAttribute("errorLogin", "Error accessing database.");
            err.printStackTrace();
        } finally {
            request.getRequestDispatcher("/login.jsp").include(request, response);
        }
    }
}
