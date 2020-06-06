package uts.isd.controller.product;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uts.isd.model.Account;
import uts.isd.model.Customer;
import uts.isd.model.Staff;
import uts.isd.controller.Validator;
import uts.isd.controller.Validator;
import uts.isd.model.Product;
import uts.isd.model.dao.AccountDAO;
import uts.isd.model.dao.CustomerDAO;
import uts.isd.model.dao.DAOException;
import uts.isd.model.dao.ProductDAO;
import uts.isd.model.dao.StaffDAO;

/**
 *
 * @author justinhyuen
 */
public class EditProductServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
            
            String ID = request.getParameter("ID");
            
        try {
            Product product = ProductDAO.get(Integer.parseInt(ID));
            request.setAttribute("product", product);
        } catch (SQLException err) {
            request.setAttribute("errorCat", "Error accessing database.");
        } catch (DAOException err) {
            request.setAttribute("errorCat", err.getMessage());
            err.printStackTrace();
        } finally {
            request.getRequestDispatcher("/products/edit.jsp").include(request, response);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
            // Create validator for the request
        Validator validator = new Validator(request);
        
        // Get form details
        String productName = request.getParameter("productName");
        String productStock = request.getParameter("productStock");
        String productDescription = request.getParameter("productDescription");
        String productCategory = request.getParameter("productCategory");
        String productPrice = request.getParameter("productPrice");
        String ID = request.getParameter("ID");
        String archived = request.getParameter("archived");

        // Run validation checks
        validator.checkEmpty(productName, productCategory)
                .validateName(productName)
                .validateproductStock(productStock)
                .validateproductDescription(productDescription)
                .validateproductCategory(productCategory)
                .validateproductPrice(productPrice);

        if (validator.failed()) {
            
            try {
            Product product = ProductDAO.get(Integer.parseInt(ID));
                request.setAttribute("product", product);
            } catch (SQLException err) {
                request.setAttribute("errorCat", "Error accessing database.");
                err.printStackTrace();
            } finally {
                request.getRequestDispatcher("/products/edit.jsp").include(request, response);
                return;
            }
            

        }
        
        try{
            Product product = ProductDAO.get(Integer.parseInt(ID));
            product.setName(productName);
            product.setStock(productStock);
            product.setPrice(productPrice);
            product.setCategory(productCategory);
            product.setDescription(productDescription);
            product.setArchived(false);
            ProductDAO.update(product);
            request.setAttribute("successEdit", true);
        }
        catch(DAOException err){
            request.setAttribute("errorCat", err.getMessage());
            err.printStackTrace();
        } catch (SQLException err) {
            request.setAttribute("errorCat", "Error accessing database.");
        } 
        
        finally{
            request.getRequestDispatcher("/products/edit.jsp").include(request, response);
        }
    }
}