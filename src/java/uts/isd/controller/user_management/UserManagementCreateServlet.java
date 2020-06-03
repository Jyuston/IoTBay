/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.controller.user_management;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import uts.isd.model.Account;
import uts.isd.model.Address;
import uts.isd.model.Customer;
import uts.isd.model.Order;
import uts.isd.model.PaymentInformation;
import uts.isd.model.Staff;
import uts.isd.model.dao.AccountDAO;
import uts.isd.model.dao.CustomerDAO;
import uts.isd.model.dao.DAOException;
import uts.isd.model.dao.StaffDAO;

/**
 *
 * @author justinhyuen
 */
public class UserManagementCreateServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //try{}
        //catch(){

        //}
        //finally{
        request.getRequestDispatcher("/user_management_create.jsp").include(request, response);
        //}
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String contactNumber = request.getParameter("contactNumber");
        String password = request.getParameter("password");
        String accountType = request.getParameter("accountType");

        try {

            if (accountType.equals("Staff") || accountType.equals("Staff-Admin")) {
                Staff newStaff = new Staff();
                newStaff.setFirstName(firstName);
                newStaff.setLastName(lastName);
                newStaff.setEmail(email);
                newStaff.setPassword(password);
                newStaff.setContactNumber(contactNumber);
                newStaff.setActive(true);
                if (accountType.equals("Staff-Admin")) {
                    newStaff.setAdmin(true);
                } else {
                    newStaff.setAdmin(false);
                }
                StaffDAO.save(newStaff);
                request.setAttribute("success", true);
                
            } 
            
            else {
                
                Customer newCustomer = new Customer();
                newCustomer.setFirstName(firstName);
                newCustomer.setLastName(lastName);
                newCustomer.setEmail(email);
                newCustomer.setPassword(password);
                newCustomer.setContactNumber(contactNumber);
                newCustomer.setActive(true);

                Address address = new Address();
                address.setAddressLine1("null");
                address.setAddressLine2("null");
                address.setSuburb(request.getParameter("null"));
                address.setPostcode("0000");
                address.setState("null");

                PaymentInformation paymentInfo = new PaymentInformation();
                LinkedList<Order> orders = new LinkedList<>();
                CustomerDAO.save(newCustomer);
                request.setAttribute("success", true);
                
            }

        } catch (SQLException err) {
            request.setAttribute("errorUserManagement", "Error accessing database.");
            err.printStackTrace();
            
        } catch (DAOException err) {
            request.setAttribute("errorUserManagement", err.getMessage());
        } finally {
            request.getRequestDispatcher("/UserManagementServlet").include(request, response);
        }
    }
}