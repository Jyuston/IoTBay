package uts.isd.controller;

import uts.isd.model.*;
import uts.isd.model.dao.CustomerDAO;

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
        // Get form details
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String contactNumber = request.getParameter("contactNumber");
        String password = request.getParameter("password");

        Address address = new Address(
                request.getParameter("addressLine1"),
                request.getParameter("addressLine2"),
                request.getParameter("suburb"),
                request.getParameter("postcode"),
                request.getParameter("state")
        );

        PaymentInformation paymentInfo = new PaymentInformation(
                request.getParameter("cardNumber"),
                request.getParameter("cvvNumber"),
                request.getParameter("expiryMonth"),
                request.getParameter("expiryYear")
        );

        // Try to log user in
        // The user ID starts null and will be auto-generated and set in CustomerDAO.save()
        try {
            LinkedList<Order> newOrderList = new LinkedList<>();
            Customer newCustomer = new Customer(
                    null,
                    firstName,
                    lastName,
                    email,
                    password,
                    contactNumber,
                    true,
                    address,
                    paymentInfo,
                    newOrderList,
                    false
            );

            // Write new customer to database
            CustomerDAO.save(newCustomer);

            // Log the customer in
            request.getSession().setAttribute("user", newCustomer);
            request.setAttribute("success", true);

            // STILL NEED TO DO VALIDATION

        } catch (SQLException err) {
            request.setAttribute("errorRegister", "Error accessing database.");
            err.printStackTrace();
        } finally {
            request.getRequestDispatcher("/register.jsp").include(request, response);
        }
    }
}
