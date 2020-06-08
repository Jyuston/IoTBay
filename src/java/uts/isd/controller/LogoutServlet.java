package uts.isd.controller;

import java.io.IOException;
import java.sql.SQLException;

import uts.isd.model.Account;
import uts.isd.model.Customer;
import uts.isd.model.dao.UserAccessDAO;
import uts.isd.model.dao.DAOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            HttpSession session = request.getSession();
            Account user = (Account) session.getAttribute("user");
            UserAccessDAO.save(user.getID(), "logout");

            if (user.isCustomer()) {
                Customer userC = (Customer) user;

                if (userC.isAnonymous()) {
                    response.sendRedirect("logout.jsp?warnAnonymous=true");
                    return;
                }
            }

            response.sendRedirect("logout.jsp");
        }
        catch (SQLException | DAOException err) {
            request.setAttribute("logoutErr", err.getMessage());
            err.printStackTrace();

            request.getRequestDispatcher("logout.jsp").include(request, response);
        }
    }
}
