package uts.isd.controller;

import uts.isd.model.*;
import uts.isd.model.dao.AccountDAO;
import uts.isd.model.dao.CustomerDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;

public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Get db connection from session
        HttpSession session = request.getSession();
        Connection dbConnection = (Connection) session.getAttribute("dbConnection");

        // Create DAOs to use in Controller
        CustomerDAO customerDAO = new CustomerDAO(dbConnection);
        AccountDAO accountDAO = new AccountDAO(dbConnection);

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
        // If any DAO method returns null, the NullPointerException catch will trigger
        try {
            String newID = accountDAO.getNextAvailableID();
            LinkedList<Order> newOrderList = new LinkedList<>();
            Customer newCustomer = new Customer(
                    newID,
                    firstName,
                    lastName,
                    email,
                    password,
                    contactNumber,
                    address,
                    paymentInfo,
                    newOrderList,
                    false
            );

            // Write new customer to database
            customerDAO.save(newCustomer);

            // Log the customer in
            session.setAttribute("user", newCustomer);

            // STILL NEED TO DO CHECKS

        } catch (SQLException err) {
            request.setAttribute("errorRegister", "Error accessing database.");
            err.printStackTrace();
        } finally {
            request.getRequestDispatcher("/register.jsp").include(request, response);
        }
    }
}
