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
        validator.checkEmpty(email, password)
                .validateEmail(email)
                .validatePassword(password)
                .validateName(firstName + " " + lastName);

        if (validator.failed()) {
            request.getRequestDispatcher("/register.jsp").include(request, response);
            return;
        }

        Address address = new Address();
        address.setAddressLine1(request.getParameter("addressLine1"));
        address.setAddressLine2(request.getParameter("addressLine1"));
        address.setSuburb(request.getParameter("suburb"));
        address.setPostcode(request.getParameter("postcode"));
        address.setState(request.getParameter("state"));

        // Payment Info is set on purchase page.
        PaymentInformation paymentInfo = new PaymentInformation();

        LinkedList<Order> orders = new LinkedList<>();

        // Create new Customer Object.
        // The Account ID starts null and will be auto-generated and set in CustomerDAO.save()
        Customer newCustomer = new Customer();
        newCustomer.setFirstName(firstName);
        newCustomer.setLastName(lastName);
        newCustomer.setEmail(email);
        newCustomer.setPassword(password);
        newCustomer.setContactNumber(contactNumber);
        newCustomer.setActive(true);
        newCustomer.setAddress(address);
        newCustomer.setPaymentInfo(paymentInfo);
        newCustomer.setOrders(orders);
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
