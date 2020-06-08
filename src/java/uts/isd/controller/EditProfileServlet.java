package uts.isd.controller;

import java.io.IOException;
import java.sql.SQLException;

import uts.isd.model.*;
import uts.isd.model.dao.AccountDAO;
import uts.isd.model.dao.CustomerDAO;
import uts.isd.model.dao.DAOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uts.isd.model.dao.UserAccessDAO;

public class EditProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute("user");
            request.setAttribute("account", account);
        } catch (DAOException err) {
            request.setAttribute("loginErr", err.getMessage());
        } finally {
            request.getRequestDispatcher("/user_management_edit.jsp").include(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Validator validator = new Validator(request);

        Account user = (Account) session.getAttribute("user");
        request.setAttribute("account", user);

        //Form Details
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String contactNumber = request.getParameter("contactNumber");
        String password = request.getParameter("password");

        String addressLine1 = request.getParameter("addressLine1");
        String addressLine2 = request.getParameter("addressLine2");
        String suburb = request.getParameter("suburb");
        String postcode = request.getParameter("postcode");
        String state = request.getParameter("state");

        String cardNumber = request.getParameter("cardNumber");
        String expiryMonth = request.getParameter("expiryMonth");
        String expiryYear = request.getParameter("expiryYear");
        String cvvNumber = request.getParameter("cvvNumber");

        // Validate account info
        validator.checkEmpty(email, password)
                .validateEmail(email)
                .validatePassword(password)
                .validateName(firstName + " " + lastName)
                .validateContactNumber(contactNumber);

        if (validator.failed()) {
            request.getRequestDispatcher("/user_management_edit.jsp").include(request, response);
            return;
        }

        try {
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setContactNumber(contactNumber);
            user.setPassword(password);

            if (user.isCustomer()) {
                Customer userC = (Customer) user;

                // Validate address
                validator.validateAddress(addressLine1)
                        .validateAddress2(addressLine2)
                        .validateSuburb(suburb)
                        .validatePostcode(postcode);

                // Validate payment info
                validator.validateCardNumber(cardNumber)
                        .validateCvv(cvvNumber)
                        .validateExpiryMonth(expiryMonth)
                        .validateExpiryYear(expiryYear);

                if (validator.failed())
                    return;

                Address address = userC.getAddress();

                address.setAddressLine1(addressLine1);
                address.setAddressLine2(addressLine2);
                address.setSuburb(suburb);
                address.setPostcode(postcode);
                address.setState(state);

                PaymentInformation paymentInfo = userC.getPaymentInfo();

                paymentInfo.setCardNumber(cardNumber);
                paymentInfo.setCvvNumber(cvvNumber);
                paymentInfo.setExpiryMonth(expiryMonth);
                paymentInfo.setExpiryYear(expiryYear);

                CustomerDAO.update(userC);
                UserAccessDAO.save(userC.getID(), "edit");
            } else {
                // When staff, just update account details
                AccountDAO.update(user);
            }

            session.setAttribute("user", user);
            request.setAttribute("account", user);
            request.setAttribute("successEdit", true);
        } catch (DAOException err) {
            request.setAttribute("errorUserManagement", err.getMessage());
            err.printStackTrace();
        } catch (SQLException err) {
            request.setAttribute("errorUserManagement", err.getMessage());
        } finally {
            request.getRequestDispatcher("/user_management_edit.jsp").include(request, response);
        }
    }
}
