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
import uts.isd.model.Customer;
import uts.isd.model.Staff;
import uts.isd.model.dao.AccountDAO;
import uts.isd.model.dao.CustomerDAO;
import uts.isd.model.dao.StaffDAO;

/**
 *
 * @author justinhyuen
 */
public class UserManagementEditServlet extends HttpServlet{
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        //try{}
        //catch(){
            
        //}
        //finally{
            request.getRequestDispatcher("/user_management_edit.jsp").include(request, response);
        //}
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        try{
            String accountID = request.getParameter("accountID");
            String accountEmail = request.getParameter("accountEmail");
            String accountPassword = request.getParameter("accountPassword");
            
            if(AccountDAO.getAccountType(accountEmail, accountPassword) == 'C'){
                Customer customer = CustomerDAO.get(Integer.parseInt(accountID));
                if(customer == null){throw new Error("No Account found.");}
                request.setAttribute("account", customer);
            }
            else if(AccountDAO.getAccountType(accountEmail, accountPassword) == 'S'){
                Staff staff = StaffDAO.get(Integer.parseInt(accountID));
                if(staff == null){throw new Error("No Account found.");}
                request.setAttribute("account", staff);
            }
        }
        
        catch(SQLException err){
            request.setAttribute("errorUserManagement", "Error accessing database.");
        }
        
        catch(Error err){
            request.setAttribute("errorUserManagement", err.getMessage());
        }
        
        finally{
            request.getRequestDispatcher("/user_management_edit.jsp").include(request, response);    
        }
    }
}
