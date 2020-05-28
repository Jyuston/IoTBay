/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.controller.usermanagement;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import uts.isd.model.Account;
import uts.isd.model.Customer;
import uts.isd.model.Staff;
import uts.isd.model.dao.AccountDAO;
import uts.isd.model.dao.CustomerDAO;
import uts.isd.model.dao.StaffDAO;

/**
 *
 * @author justinhyuen
 */
public class UserManagementServlet extends HttpServlet {

            
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        
        try {
            HttpSession session = request.getSession();
            
            List<Customer> customerList = CustomerDAO.getAll();
            List<Staff> staffList = StaffDAO.getAll();
            
            request.setAttribute("customerList", customerList);
            request.setAttribute("staffList", staffList);
            //request.getAttribute in jsp
        } 
        
        catch(SQLException err) {
            request.setAttribute("errorUserManagement", "Error accessing database.");
            err.printStackTrace();
            
        } 
        
        finally {
            request.getRequestDispatcher("/user_management.jsp").include(request, response);
        }
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //form deets
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String contactNumber = request.getParameter("contactNumber");
  
       try {
            Account resultAccount = AccountDAO.getAccount(firstName, lastName, contactNumber);
            request.setAttribute("resultAccount", resultAccount);
        }
        
       catch(SQLException err) {
            request.setAttribute("errorUserManagement", "Error accessing database.");
        }
       
       finally {
           request.getRequestDispatcher("/user_management.jsp").include(request, response);
           
       }

    }
    
}
