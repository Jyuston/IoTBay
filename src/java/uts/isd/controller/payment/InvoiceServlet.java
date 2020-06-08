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
import java.util.List;

public class InvoiceServlet extends HttpServlet {

    /*
    private void getOrders(HttpServletRequest request) throws SQLException{
        List<Customer> customerList = CustomerDAO.getAll();
        request.setAttribute("customerList", customerList);
    }
    */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //if they search get the items then update so it displays the credentials
        //if they don't they don't search then display the credentials anyway
        //Use the OrderDAO to get orderID# passing in the ID of the customer
        //would use customer ID to look up any column with that ID returning the column
        //OrderDAO.getOrders(customerID); return type List
        //save what orderDAO returned into a variable
        //Store the variable onto the request

        String orderId = request.getParameter("orderId");



        try {
            HttpSession session = request.getSession();
            //getOrders(request);
            Order invoiceResult = OrderDAO.get(Integer.parseInt(orderId));
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