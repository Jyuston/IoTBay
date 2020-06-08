package uts.isd.controller.order;

import uts.isd.model.Order;
import uts.isd.model.dao.DAOException;
import uts.isd.model.dao.OrderDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

public class FilterOrdersServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String customerID = request.getParameter("customerID");

        LinkedList<Order> orders;

        try {
            if (customerID == null && (startDate == null || endDate == null)) {
                orders = OrderDAO.getAll();
            }
            else if (customerID != null && (startDate == null || endDate == null)) {
                orders = OrderDAO.getAllByCustomer(Integer.parseInt(customerID));
            }
            else if (customerID == null && (startDate != null && startDate != null)) {
                orders = OrderDAO.getAll(startDate, endDate);
            }
            else {
                orders = OrderDAO.getAllByCustomer(Integer.parseInt(customerID), startDate, endDate);
            }

            request.setAttribute("filteredOrders", orders);
        }
        catch (SQLException | DAOException err) {
            request.setAttribute("updateError", err.getMessage());
            err.printStackTrace();
        }
        finally {
            request.getRequestDispatcher("history.jsp").include(request, response);
        }
    }
}