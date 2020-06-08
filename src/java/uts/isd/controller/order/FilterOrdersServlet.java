package uts.isd.controller.order;

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
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FilterOrdersServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer user = (Customer) session.getAttribute("user");

        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String orderID = request.getParameter("orderID");

        List<Order> orders;

        try {
            if (orderID != null) {
                Predicate<Order> byID = order -> order.getID() == Integer.parseInt(orderID);
                orders = user.getOrders().stream().filter(byID).collect(Collectors.toList());
            } else {
                orders = OrderDAO.getAllByCustomer(user.getID(), startDate, endDate);
            }

            request.setAttribute("filteredOrders", orders);
            request.setAttribute("filtered", true);
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