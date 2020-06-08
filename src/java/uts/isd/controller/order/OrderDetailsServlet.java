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

public class OrderDetailsServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int ID = Integer.parseInt(request.getParameter("ID"));

        try {
            Order order = OrderDAO.get(ID);
            request.setAttribute("order", order);
        }
        catch (DAOException err) {
            request.setAttribute("orderDetailsErr", err.getMessage());
        }
        catch (SQLException err) {
            request.setAttribute("orderDetailsErr", "Error accessing database.");
            err.printStackTrace();
        }
        finally {
            request.getRequestDispatcher("details.jsp").include(request, response);
        }
    }
}
