
package uts.isd.controller.product;

import java.sql.SQLException;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uts.isd.model.Product;
import uts.isd.model.dao.DAOException;
import uts.isd.model.dao.ProductDAO;

public class CatalogueServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        String query = request.getParameter("query");
        String category = request.getParameter("category");

        request.setAttribute("prevQuery", query);

        LinkedList<Product> productList;

        try {
            productList = ProductDAO.search(query, category);
            request.setAttribute("productList", productList);
        }
        catch (SQLException err) {
            request.setAttribute("catalogueErr", "Error accessing database.");
            err.printStackTrace();
        }
        finally {
            request.getRequestDispatcher("/index.jsp").include(request, response);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        int productID = Integer.parseInt(request.getParameter("productID"));

        try {
            Product searchedProduct = ProductDAO.get(productID);
            request.setAttribute("searchedProduct", searchedProduct);
        }
        catch (DAOException err) {
            request.setAttribute("catalogueErr", err.getMessage());
        }
        catch (SQLException err) {
            request.setAttribute("catalogueErr", "Error accessing database.");
            err.printStackTrace();
        }
        finally {
            request.getRequestDispatcher("/index.jsp").include(request, response);
        }
    }
}
