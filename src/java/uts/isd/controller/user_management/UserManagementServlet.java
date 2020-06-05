/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.controller.user_management;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import uts.isd.model.Account;
import uts.isd.model.Customer;
import uts.isd.model.Staff;
import uts.isd.controller.Validator;
import uts.isd.model.dao.AccountDAO;
import uts.isd.model.dao.CustomerDAO;
import uts.isd.model.dao.DAOException;
import uts.isd.model.dao.StaffDAO;

/**
 *
 * @author justinhyuen
 */
public class UserManagementServlet extends HttpServlet {

    private void getTables(HttpServletRequest request) throws SQLException{
            List<Customer> customerList = CustomerDAO.getAll();
            List<Staff> staffList = StaffDAO.getAll();
            
            request.setAttribute("customerList", customerList);
            request.setAttribute("staffList", staffList);
    }
            
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        
        try {
            HttpSession session = request.getSession(); 
            getTables(request);
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
        
        Validator validator = new Validator(request);

        //form deets
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String contactNumber = request.getParameter("contactNumber");
        
        validator.validateName(firstName + " " + lastName)
                .validateContactNumber(contactNumber);

        if (validator.failed()) {
            try {
                getTables(request);
            } catch (SQLException err) {
                request.setAttribute("errorUserManagement", "Error accessing database.");
            }
            request.getRequestDispatcher("/user_management.jsp").include(request, response);
            return;
        }
  
       try {
            HttpSession session = request.getSession(); 
            getTables(request);
            Account resultAccount = AccountDAO.getAccount(firstName, lastName, contactNumber);
            if(resultAccount == null){throw new Error("No Account found.");}
            request.setAttribute("resultAccount", resultAccount);
        }
        
       catch(SQLException err) {
            request.setAttribute("errorUserManagement", "Error accessing database.");
        }
       catch(DAOException err){
           request.setAttribute("errorUserManagement", err.getMessage());
           err.printStackTrace();
        }
       
       finally {
           request.getRequestDispatcher("/user_management.jsp").include(request, response);    
        }

    }
    
}
