package uts.isd.controller.product;

import uts.isd.controller.Validator;
import uts.isd.model.Product;
import uts.isd.model.dao.DAOException;
import uts.isd.model.dao.ProductDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class AddProductServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        // Create validator for request
        Validator validator = new Validator(request);

        // Get form details
        String name = request.getParameter("name");
        String category = request.getParameter("category");
        String description = request.getParameter("description");
        String price = request.getParameter("price");
        String stock = request.getParameter("stock");

        // Run validation checks
        validator.checkEmpty(name, "Name")
                .checkEmpty(category, "Category")
                .checkEmpty(description, "Description")
                .checkProductPrice(price)
                .checkProductStock(stock);

        if (validator.failed()) {
            request.getRequestDispatcher("add.jsp").include(request, response);
            return;
        }

        Product newProduct = new Product();

        newProduct.setName(name);
        newProduct.setCategory(category);
        newProduct.setDescription(description);
        newProduct.setPrice(Double.parseDouble(price));
        newProduct.setStock(Integer.parseInt(stock));
        newProduct.setArchived(false);

        try {
            ProductDAO.save(newProduct);
            request.setAttribute("newProductID", newProduct.getID());
        }
        catch (DAOException err) {
            request.setAttribute("addProductErr", err.getMessage());
        }
        catch (SQLException err) {
            request.setAttribute("addProductErr", "Error accessing the Database.");
            err.printStackTrace();
        }
        finally {
            // Display add product form, including any errors/msgs that occurred during the post request
            request.getRequestDispatcher("add.jsp").include(request, response);
        }
    }
}
