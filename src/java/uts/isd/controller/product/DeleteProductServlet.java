package uts.isd.controller.product;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uts.isd.model.Customer;
import uts.isd.model.Product;
import uts.isd.model.Staff;

import uts.isd.model.dao.DAOException;
import uts.isd.model.dao.ProductDAO;
import uts.isd.model.dao.StaffDAO;

/**
 *
 */
public class DeleteProductServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int productID = Integer.parseInt(request.getParameter("ID"));
        
        try {
            ProductDAO.archive(productID);
            request.setAttribute("successDelete", true);

            List<Product> productList = ProductDAO.getAll();
            request.setAttribute("productList", productList);
        }
        catch (DAOException err) {
            request.setAttribute("deleteProductErr", err.getMessage());
        }
        catch (SQLException err) {
            request.setAttribute("deleteProductErr", "Error accessing database.");
            err.printStackTrace();
        }
        finally {
            request.getRequestDispatcher("index.jsp").include(request, response);
        }
    }
}
