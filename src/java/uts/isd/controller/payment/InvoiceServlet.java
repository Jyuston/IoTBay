package uts.isd.controller.payment;

import uts.isd.controller.Validator;
import uts.isd.model.Customer;
import uts.isd.model.Order;
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

public class InvoiceServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        try {
            HttpSession session = request.getSession();
            Customer user = (Customer) session.getAttribute("user");

            LinkedList<Order> invoiceResults = OrderDAO.getAllByCustomer(user.getID(), startDate, endDate);
            request.setAttribute("invoiceResults", invoiceResults);
            request.setAttribute("filtered", true);
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderID = request.getParameter("orderId");

        Validator kevinId = new Validator(request);
        try {
            HttpSession session = request.getSession();
            kevinId.validateID(orderID);
            if(kevinId.failed()){
                return;
            }
            Order invoiceResult = OrderDAO.get(Integer.parseInt(orderID));
            request.setAttribute("invoiceResult", invoiceResult);
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