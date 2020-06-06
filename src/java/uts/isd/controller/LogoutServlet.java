package uts.isd.controller;

import java.io.IOException;
import java.sql.SQLException;

import uts.isd.model.Account;
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
        
        // Log user out
        try {
            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute("user");
                UserAccessDAO.save(account.getID(),"logout");
        }
        
        catch (DAOException err) {
            request.setAttribute("loginErr", err.getMessage());
        }
        
        catch (SQLException err) {
            request.setAttribute("logoutErr", "Error accessing database.");
            err.printStackTrace();
        }
        finally {
            request.getRequestDispatcher("/logout.jsp").include(request, response);
        }
    }
}
