package uts.isd.controller.product;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uts.isd.controller.Validator;
import uts.isd.model.Product;
import uts.isd.model.dao.DAOException;
import uts.isd.model.dao.ProductDAO;

public class EditProductServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int ID = Integer.parseInt(request.getParameter("ID"));

        try {
            Product product = ProductDAO.get(ID);
            request.setAttribute("product", product);
        }
        catch (DAOException err) {
            request.setAttribute("editProductErr", err.getMessage());
        }
        catch (SQLException err) {
            request.setAttribute("editProductErr", "Error accessing database.");
            err.printStackTrace();
        }
        finally {
            request.getRequestDispatcher("edit.jsp").include(request, response);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Create validator for the request
        Validator validator = new Validator(request);

        // Get form details
        int ID = Integer.parseInt(request.getParameter("ID"));
        String name = request.getParameter("name");
        String category = request.getParameter("category");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        int stock = Integer.parseInt(request.getParameter("stock"));

        // Run validation checks
        validator
                .checkEmpty(name, "Name")
                .checkEmpty(category, "Category")
                .checkEmpty(description, "Description")
                .checkNegativePrice(price)
                .checkNegativeStock(stock);

        try {
            Product product = ProductDAO.get(ID);
            request.setAttribute("product", product);

            if (validator.failed())
                return;

            product.setName(name);
            product.setStock(stock);
            product.setPrice(price);
            product.setCategory(category);
            product.setDescription(description);

            ProductDAO.update(product);

            request.setAttribute("successEdit", true);
        }
        catch (DAOException err) {
            request.setAttribute("editProductErr", err.getMessage());
        }
        catch (SQLException err) {
            request.setAttribute("editProductErr", "Error accessing database.");
            err.printStackTrace();
        }
        finally {
            request.getRequestDispatcher("edit.jsp").include(request, response);
        }
    }
}
