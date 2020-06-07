package uts.isd.controller.product;

import uts.isd.model.Product;
import uts.isd.model.dao.DAOException;
import uts.isd.model.dao.ProductDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ProductDetailsServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int ID = Integer.parseInt(request.getParameter("ID"));

        try {
            Product product = ProductDAO.get(ID);
            request.setAttribute("product", product);
        }
        catch (DAOException err) {
            request.setAttribute("productDetailsErr", err.getMessage());
        }
        catch (SQLException err) {
            request.setAttribute("productDetailsErr", "Error accessing database.");
            err.printStackTrace();
        }
        finally {
            request.getRequestDispatcher("details.jsp").include(request, response);
        }
    }
}
