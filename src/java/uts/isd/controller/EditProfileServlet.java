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
import uts.isd.model.Customer;
import uts.isd.model.dao.UserAccessDAO;

public class EditProfileServlet extends HttpServlet {
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
        
        validator.checkEmpty(email, password)
                .validateEmail(email)
                .validatePassword(password)
                .validateName(firstName + " " + lastName)
                .validateContactNumber(contactNumber);

        
        try {
            HttpSession session = request.getSession();
            Account account = AccountDAO.getAccount(Integer.parseInt(ID));
            request.setAttribute("account", account);
            
            if (validator.failed()) {
                return;
            }
            
            account.setFirstName(firstName);
            account.setLastName(lastName);
            account.setEmail(email);
            account.setContactNumber(contactNumber);
            account.setPassword(password);
            
            if (account.isCustomer()){
                
               Customer accountC = (Customer) account;
                
               String addressLine1 = request.getParameter("addressLine1");
               String addressLine2 = request.getParameter("addressLine2");
               String suburb = request.getParameter("suburb");
               String postcode = request.getParameter("postcode");
               String state = request.getParameter("state");
               
               String cardNumber = request.getParameter("cardNumber");
               String expiryMonth = request.getParameter("expiryMonth");
               String expiryYear = request.getParameter("expiryYear");
               String cvvNumber = request.getParameter("cvvNumber");
               
               validator.validateAddress(addressLine1)
                        .validateAddress2(addressLine2)
                        .validateSuburb(suburb)
                        .validatePostcode(postcode)
                        .validateCardNumber(cardNumber)
                        .validateCvv(cvvNumber)
                        .validateExpiryMonth(expiryMonth)
                        .validateExpiryYear(expiryYear);

               if (validator.failed()) {
                  return;
               }               
               
               accountC.getAddress().setAddressLine1(addressLine1);
               accountC.getAddress().setAddressLine2(addressLine2);
               accountC.getAddress().setSuburb(suburb);
               accountC.getAddress().setPostcode(postcode);
               accountC.getAddress().setState(state);
               
               accountC.getPaymentInfo().setCardNumber(cardNumber);
               accountC.getPaymentInfo().setCvvNumber(cvvNumber);
               accountC.getPaymentInfo().setExpiryMonth(expiryMonth);
               accountC.getPaymentInfo().setExpiryYear(expiryYear);
               
               try{
                    CustomerDAO.update(accountC);
                    UserAccessDAO.save(accountC.getID(),"edit");
                    session.setAttribute("user", accountC);
               } catch(DAOException err){
                    request.setAttribute("errorUserManagement", err.getMessage());
                    err.printStackTrace();
               } catch(SQLException err) {
                   request.setAttribute("errorUserManagement", "Error accessing database.");
               }
               
            }
            
            AccountDAO.update(account);
            request.setAttribute("successEdit", true);
            request.setAttribute("account", account);
            if (account.isStaff()){
            session.setAttribute("user", account);
            }
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
