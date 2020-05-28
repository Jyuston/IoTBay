
package uts.isd.controller;

import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uts.isd.model.Product;
import uts.isd.model.dao.ProductDAO;

public class ProductServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        try {
            List<Product> productList = ProductDAO.getAll();
            request.setAttribute("productList", productList);
        } catch (SQLException err) {
            request.setAttribute("errorCatalogue", "Error accessing database.");
            err.printStackTrace();
        } finally {
            request.getRequestDispatcher("/catalogue.jsp").include(request, response);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        String name = request.getParameter("pName");
        String category = request.getParameter("pCat");

        try {
            // to generate list of products based on that name and type.
            Product productsNC = ProductDAO.getRead(name, category);

            request.setAttribute("productsNC", productsNC);


        } catch (SQLException err) {
            request.setAttribute("errorCatalogue", "Error accessing database.");
        } finally {
            request.getRequestDispatcher("/catalogue.jsp").include(request, response);

        }

    }
}
