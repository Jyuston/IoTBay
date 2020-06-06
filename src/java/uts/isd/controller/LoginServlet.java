package uts.isd.controller;

import java.io.IOException;
import java.sql.SQLException;

import uts.isd.model.Account;
import uts.isd.model.dao.AccountDAO;
import uts.isd.model.dao.CustomerDAO;
import uts.isd.model.dao.DAOException;
import uts.isd.model.dao.StaffDAO;
import uts.isd.model.dao.UserAccessDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Create validator for the request
        Validator validator = new Validator(request);

        // Get form details
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Run validation checks
        validator.checkEmpty(email, password)
                .validateEmail(email)
                .validatePassword(password);

        if (validator.failed()) {
            request.getRequestDispatcher("/login.jsp").include(request, response);
            return;
        }

        // Try to log user in
        try {
            char accountType = AccountDAO.getAccountType(email, password);

            Account account = (accountType == 'C') ?
                    CustomerDAO.get(email, password) :
                    StaffDAO.get(email, password);
            UserAccessDAO.save(account.getID(),"login");
            request.getSession().setAttribute("user", account);
        }
        catch (DAOException err) {
            request.setAttribute("loginErr", err.getMessage());
        }
        catch (SQLException err) {
            request.setAttribute("loginErr", "Error accessing database.");
            err.printStackTrace();
        }
        finally {
            request.getRequestDispatcher("/login.jsp").include(request, response);
        }
    }
}
