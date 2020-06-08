package uts.isd.controller;

import java.io.IOException;
import java.sql.SQLException;

import java.util.List;

import uts.isd.model.Log;
import uts.isd.model.Account;

import uts.isd.model.dao.DAOException;
import uts.isd.model.dao.UserAccessDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LogsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("user");
        List<Log> userLog = UserAccessDAO.getLogs(account.getID());

        request.setAttribute("userLogs", userLog);
        }
        catch (DAOException err) {
            request.setAttribute("loginErr", err.getMessage());
        }
        catch (SQLException err) {
            request.setAttribute("loginErr", "Error accessing database.");
            err.printStackTrace();
        }
        finally {
            request.getRequestDispatcher("/logs.jsp").include(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String date = request.getParameter("date");
        
        try {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("user");
        List<Log> dateList = UserAccessDAO.getDate(account.getID(), date);
        request.setAttribute("userLogs", dateList);
        }

        catch (SQLException err) {
            request.setAttribute("logErr", "Error accessing database.");
            err.printStackTrace();
        }

        finally {
            request.getRequestDispatcher("/logs.jsp").include(request, response);
        }
        
    }
}

   

