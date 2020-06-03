/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.controller.user_management;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uts.isd.model.Account;
import uts.isd.model.Customer;
import uts.isd.model.Staff;
import uts.isd.model.dao.AccountDAO;
import uts.isd.model.dao.CustomerDAO;
import uts.isd.model.dao.DAOException;
import uts.isd.model.dao.StaffDAO;

/**
 *
 * @author justinhyuen
 */
public class UserManagementEditServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
            
            String accountID = request.getParameter("accountID");
            String accountFirstName = request.getParameter("accountFirstName");
            String accountLastName = request.getParameter("accountLastName");
            String accountContactNumber = request.getParameter("accountContactNumber");
            
        try {
            Account account = AccountDAO.getAccount(accountFirstName, accountLastName, accountContactNumber);
            request.setAttribute("account", account);
        } catch (SQLException err) {
            request.setAttribute("errorUserManagement", "Error accessing database.");
        } catch (DAOException err) {
            request.setAttribute("errorUserManagement", err.getMessage());
            err.printStackTrace();
        } finally {
            request.getRequestDispatcher("/user_management_edit.jsp").include(request, response);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }
}
