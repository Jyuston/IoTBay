package uts.isd.controller.payment;

import uts.isd.controller.Validator;
import uts.isd.controller.product.AddProductServlet;
import uts.isd.model.Account;
import uts.isd.model.Address;
import uts.isd.model.Customer;
import uts.isd.model.PaymentInformation;
import uts.isd.model.dao.CustomerDAO;
import uts.isd.model.dao.DAOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class EditPaymentInfoServlet extends HttpServlet {
    //doPost for updating Data
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Validator validator = new Validator(request);

        HttpSession session = request.getSession();
        Customer user = (Customer) session.getAttribute("user");

        String addressLine1 = request.getParameter("addressLine1");
        String addressLine2 = request.getParameter("addressLine2");
        String suburb = request.getParameter("suburb");
        String postcode = request.getParameter("postcode");
        String state = request.getParameter("state");
        String cardNumber = request.getParameter("cardNumber");
        String expiryMonth = request.getParameter("expiryMonth");
        String expiryYear = request.getParameter("expiryYear");
        String cvvNumber = request.getParameter("cvvNumber");

        Address address = user.getAddress();

        address.setAddressLine1(addressLine1);
        address.setAddressLine2(addressLine2);
        address.setSuburb(suburb);
        address.setPostcode(postcode);
        address.setState(state);

        PaymentInformation paymentInfo = user.getPaymentInfo();

        paymentInfo.setCardNumber(cardNumber);
        paymentInfo.setCvvNumber(cvvNumber);
        paymentInfo.setExpiryMonth(expiryMonth);
        paymentInfo.setExpiryYear(expiryYear);


        try {
            CustomerDAO.update(user);
            session.setAttribute("user", user);

            request.setAttribute("successEdit", true);
        }
        catch (DAOException err) {
            request.setAttribute("editErr", err.getMessage());
        }
        catch (SQLException err) {
            request.setAttribute("editErr", "Error accessing database.");
            err.printStackTrace();
        }
        finally {
            request.getRequestDispatcher("/edit_payment_info.jsp").include(request, response);
        }
    }
}
