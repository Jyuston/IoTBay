package uts.isd.controller;

import uts.isd.model.*;
import uts.isd.model.dao.CustomerDAO;
import uts.isd.model.dao.DAOException;
import uts.isd.model.dao.UserAccessDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Create validator for the request
        Validator validator = new Validator(request);

        // Get form details
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String contactNumber = request.getParameter("contactNumber");
        String password = request.getParameter("password");

        // Run validation checks
        validator.checkEmptyEmailPass(email, password)
                .validateEmail(email)
                .validatePassword(password)
                .validateName(firstName + " " + lastName)
                .validateContactNumber(contactNumber);

        if (validator.failed()) {
            request.getRequestDispatcher("/register.jsp").include(request, response);
            return;
        }

        // Payment Info is set on purchase page.
        // Create new Customer Object.
        // The Account ID starts null and will be auto-generated and set in CustomerDAO.save()
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
        newCustomer.setAnonymous(false);

        try {
            // Write new customer to database
            CustomerDAO.save(newCustomer);
            UserAccessDAO.save(newCustomer.getID(),"create_account");
            // Log the customer in
            request.getSession().setAttribute("user", newCustomer);
            request.setAttribute("success", true);
        }
        catch (DAOException err) {
            request.setAttribute("registerErr", err.getMessage());
        }
        catch (SQLException err) {
            request.setAttribute("registerErr", "Error accessing database.");
            err.printStackTrace();
        }
        finally {
            request.getRequestDispatcher("/register.jsp").include(request, response);
        }
    }
}
