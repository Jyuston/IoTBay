package uts.isd.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import uts.isd.model.Account;
import uts.isd.model.dao.StaffDAO;
import uts.isd.model.dao.AccountDAO;
import uts.isd.model.dao.CustomerDAO;
import uts.isd.model.dao.DAOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class editProfileServlet extends HttpServlet {
    @Override 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("user");
        request.setAttribute("account", account);
    }
    catch (DAOException err) {
        request.setAttribute("loginErr", err.getMessage());
    }
    finally {
        request.getRequestDispatcher("/user_management_edit.jsp").include(request, response);
    }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
       
        Validator validator = new Validator(request);

        //Form Details
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String contactNumber = request.getParameter("contactNumber");
        String password = request.getParameter("password");
        String ID = request.getParameter("ID");
        
        validator.validateName(firstName + " " + lastName)
                .validateContactNumber(contactNumber);

        if (validator.failed()) {
            try {
                Account account = AccountDAO.getAccount(Integer.parseInt(ID));
                request.setAttribute("account", account);
            } catch (SQLException err) {
                request.setAttribute("errorUserManagement", "Error accessing database.");
                err.printStackTrace();
            } finally {
                request.getRequestDispatcher("/user_management_edit.jsp").include(request, response);
                return;
            }
        }
        try {
            HttpSession session = request.getSession();
            Account account = AccountDAO.getAccount(Integer.parseInt(ID));
            account.setFirstName(firstName);
            account.setLastName(lastName);
            account.setEmail(email);
            account.setContactNumber(contactNumber);
            account.setPassword(password);
            AccountDAO.update(account);
            request.setAttribute("successEdit", true);
            request.setAttribute("account", account);
            session.setAttribute("user", account);
        }
        catch (DAOException err) {
            request.setAttribute("errorUserManagement", err.getMessage());
            err.printStackTrace();           
        }
        catch (SQLException err) { 
            request.setAttribute("errorUserManagement", err.getMessage());
            }
        finally {
            request.getRequestDispatcher("/user_management_edit.jsp").include(request, response);
        }
    }
}
