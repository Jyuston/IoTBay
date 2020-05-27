/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.controller.UserManagement;
import javax.servlet.http.*;
import uts.isd.model.dao.AccountDAO;
import uts.isd.model.dao.CustomerDAO;
import uts.isd.model.dao.StaffDAO;

/**
 *
 * @author justinhyuen
 */
public class UserManagement extends HttpServlet {
    private static final AccountDAO AccDAO = new AccountDAO();
    private static final CustomerDAO CustDAO = new CustomerDAO();
    private static final StaffDAO StffDAO = new StaffDAO(); 
            
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        //search the database 
        //res render 
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response){ 
    }
    
}
