/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.controller.user_management;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import uts.isd.model.Address;
import uts.isd.model.Customer;
import uts.isd.model.Order;
import uts.isd.model.PaymentInformation;
import uts.isd.model.Staff;
import uts.isd.controller.Validator;
import uts.isd.model.dao.CustomerDAO;
import uts.isd.model.dao.DAOException;
import uts.isd.model.dao.StaffDAO;
import uts.isd.model.dao.UserAccessDAO;

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

        Validator validator = new Validator(request);
        
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String contactNumber = request.getParameter("contactNumber");
        String password = request.getParameter("password");
        String accountType = request.getParameter("accountType");
        
        //Run validation checks
        validator.checkEmpty(email, password)
                .validateName(firstName + " " + lastName)
                .validateEmail(email)
                .validatePassword(password)
                .validateContactNumber(contactNumber);

        if (validator.failed()) {
            request.getRequestDispatcher("/user_management_create.jsp").include(request, response);
            return;
        }

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
            } 
            
            else {
                Customer newCustomer = new Customer();
                newCustomer.setFirstName(firstName);
                newCustomer.setLastName(lastName);
                newCustomer.setEmail(email);
                newCustomer.setPassword(password);
                newCustomer.setContactNumber(contactNumber);
                newCustomer.setActive(true);

                newCustomer.setAddress(new Address());
                newCustomer.setPaymentInfo(new PaymentInformation());
                newCustomer.setOrders(new LinkedList<>());
                
                CustomerDAO.save(newCustomer);       
            }

        } catch (SQLException err) {
            request.setAttribute("errorUserManagement", "Error accessing database.");
            err.printStackTrace();
            
        } catch (DAOException err) {
            request.setAttribute("errorUserManagement", err.getMessage());
        } finally {
            response.sendRedirect("UserManagementServlet?success=true");
        }
    }
}
