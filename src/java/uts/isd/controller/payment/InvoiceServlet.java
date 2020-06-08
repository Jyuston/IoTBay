package uts.isd.controller.payment;

import uts.isd.model.Account;
import uts.isd.model.Customer;
import uts.isd.model.Order;
import uts.isd.model.dao.AccountDAO;
import uts.isd.model.dao.CustomerDAO;
import uts.isd.model.dao.DAOException;
import uts.isd.model.dao.OrderDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class InvoiceServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        try {
            HttpSession session = request.getSession();
            Customer user = (Customer) session.getAttribute("user");
            //getOrders(request);
            LinkedList<Order> invoiceResults = OrderDAO.getInvoices(user, startDate, endDate);
            request.setAttribute("invoiceResults", invoiceResults);
        }

        catch(SQLException err) {
            request.setAttribute("errorUserManagement", "Error accessing database.");
        }

        catch (DAOException err) {
            request.setAttribute("editErr", err.getMessage());
        }

        finally {
            request.getRequestDispatcher("/invoice.jsp").include(request, response);
        }
    }
}