/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.controller.user_management;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
public class UserManagementDeleteServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
            
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String accountID = request.getParameter("accountID");
        
        try {
            AccountDAO.delete(Integer.parseInt(accountID));
            request.setAttribute("successDelete", true); 
        } 
        
        catch (SQLException err) {
            request.setAttribute("errorUserManagement", "Error accessing database.");
        } 
        
        catch (DAOException err) {
            request.setAttribute("errorUserManagement", err.getMessage());
        } 
        
        finally {
            try {
                List<Customer> customerList = CustomerDAO.getAll();
                List<Staff> staffList = StaffDAO.getAll();
                request.setAttribute("customerList", customerList);
                request.setAttribute("staffList", staffList);
            }
            
            catch(SQLException err){
                request.setAttribute("errorUserManagement", "Error accessing database.");
            }

            finally{
                request.getRequestDispatcher("/user_management.jsp").include(request, response);
            }
        }
    }
}

