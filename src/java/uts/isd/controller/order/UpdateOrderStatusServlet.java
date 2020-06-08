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

public class UpdateOrderStatusServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int ID = Integer.parseInt(request.getParameter("ID"));
        String status = request.getParameter("status");

        try {
            Order order = OrderDAO.get(ID);

            order.setStatus(status);
            OrderDAO.update(order);

            request.setAttribute("order", order);
            request.setAttribute("successUpdate", true);
        }
        catch (SQLException | DAOException err) {
            request.setAttribute("updateError", err.getMessage());
            err.printStackTrace();
        }
        finally {
            request.getRequestDispatcher("details.jsp").include(request, response);
        }
    }
}