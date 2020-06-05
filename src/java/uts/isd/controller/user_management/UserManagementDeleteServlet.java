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
import uts.isd.model.dao.AccountDAO;
import uts.isd.model.dao.DAOException;

/**
 *
 * @author justinhyuen
 */
public class UserManagementDeleteServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
            
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String accountID = request.getParameter("accountID");
        try {
            
            AccountDAO.delete(accountID);
            request.setAttribute("successDelete", true); 
        } 
        
        catch (SQLException err) {
            request.setAttribute("errorUserManagement", "Error accessing database.");
        } 
        
        catch (DAOException err) {
            request.setAttribute("errorUserManagement", err.getMessage());
        } 
        
        finally {
            request.getRequestDispatcher("/UserManagementServlet").include(request, response);
        }
    }
}

