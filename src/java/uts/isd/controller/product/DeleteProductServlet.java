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
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
            
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String productID = request.getParameter("productID");
        
        try {
            ProductDAO.delete(Integer.parseInt(productID));
            request.setAttribute("successDelete", true); 
        } 
        
        catch (SQLException err) {
            request.setAttribute("errorCat", "Error accessing database.");
        } 
        
        catch (DAOException err) {
            request.setAttribute("errorCat", err.getMessage());
        } 
        
        finally {
            try {
                List<Product> productList = ProductDAO.getAll();
                request.setAttribute("productList", productList);
            }
            
            catch(SQLException err){
                request.setAttribute("errorCat", "Error accessing database.");
            }

            finally{
                request.getRequestDispatcher("/CatalogueServlet.jsp").include(request, response);
                System.out.println("Product has been deleted");
            }
        }
    }
}