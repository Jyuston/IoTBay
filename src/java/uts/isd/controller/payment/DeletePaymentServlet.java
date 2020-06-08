package uts.isd.controller.payment;

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

public class DeletePaymentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        Customer user = (Customer) session.getAttribute("user");

        user.setAddress(new Address());
        user.setPaymentInfo(new PaymentInformation());

        try {
            CustomerDAO.update(user);
            session.setAttribute("user", user);

            request.setAttribute("successDelete", true);
        }
        catch (DAOException err) {
            request.setAttribute("deleteErr", err.getMessage());
        }
        catch (SQLException err) {
            request.setAttribute("deleteErr", "Error accessing database.");
            err.printStackTrace();
        }
        finally {
            request.getRequestDispatcher("/checkout.jsp").include(request, response);
        }
    }
}
