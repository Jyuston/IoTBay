package uts.isd.controller.order;

import uts.isd.model.Customer;
import uts.isd.model.Order;
import uts.isd.model.OrderLineItem;
import uts.isd.model.dao.DAOException;
import uts.isd.model.dao.OrderDAO;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

public class PlaceOrderServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws java.io.IOException {
        HttpSession session = request.getSession();
        Customer user = (Customer) session.getAttribute("user");
        LinkedList<OrderLineItem> cart = (LinkedList<OrderLineItem>) session.getAttribute("cart");

        Order order = new Order();
        order.setCustomer(user);
        order.setOrderedProducts(cart);
        order.setTrackingID("TRACK_ORD_" + ThreadLocalRandom.current().nextInt(0, 1000));
        order.setShippingAddress(user.getAddress().dbStringFormat());
        order.setCardUsed(user.getPaymentInfo().getCardNumber());

        try {
            int newOrderID = OrderDAO.save(order);

            user.getOrders().add(order);

            session.setAttribute("user", user);
            session.setAttribute("cart", new LinkedList<>());

            response.sendRedirect("OrderDetailsServlet?ID=" + newOrderID + "&successAdd=true");
        } catch (DAOException err) {
            request.setAttribute("orderErr", err.getMessage());
            response.sendRedirect("cart.jsp");
        } catch (SQLException err) {
            request.setAttribute("orderErr", err.getMessage());
            err.printStackTrace();
            response.sendRedirect("cart.jsp");
        }
    }
}