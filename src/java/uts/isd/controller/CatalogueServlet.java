
package uts.isd.controller;

import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uts.isd.model.Product;
import uts.isd.model.dao.ProductDAO;

public class CatalogueServlet extends HttpServlet {
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
        String name = request.getParameter("name");
        String category = request.getParameter("category");

        try {
            Product searchedProduct = ProductDAO.get(name, category);

            if (searchedProduct == null)
                throw new Error("No product found.");

            request.setAttribute("searchedProduct", searchedProduct);
        } catch (Error err) {
            request.setAttribute("errorCatalogue", err.getMessage());
        } catch (SQLException err) {
            request.setAttribute("errorCatalogue", "Error accessing database.");
            err.printStackTrace();
        } finally {
            request.getRequestDispatcher("/catalogue.jsp").include(request, response);

        }

    }
}
