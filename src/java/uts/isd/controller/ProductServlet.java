
package uts.isd.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.isd.model.Account;
import uts.isd.model.Product;
import uts.isd.model.dao.DAO;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.ProductDAO;

public class ProductServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        try {
            HttpSession session = request.getSession();

            List<Product> productList = ProductDAO.getAll();
            request.setAttribute("productList", productList);
        } 
        
        catch(SQLException err) {
            request.setAttribute("errorCatalogue", "Error accessing database.");
            err.printStackTrace();
            
        } 
        
        finally {
            request.getRequestDispatcher("/Catalogue.jsp").include(request, response);
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
        
              
              
            }

            catch(SQLException err) {
            request.setAttribute("errorCatalogue", "Error accessing database.");
        }
       
            finally {
           request.getRequestDispatcher("/Catalogue.jsp").include(request, response);
           
       }
            
        }
    }

       
    
    
    
    
    /*
    public static Product create(String name, int stock, double price, String category, boolean archived) throws SQLException {
        Account user = session.getAttribute("user");
        if (user.getAccountType(user.getEmail(), user.getPassword()) == 'S') {
        
            String ID;
        try {
            ID = ProductDAO.getNextAvailableID();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        Product createdProduct = new Product(
                name,
                stock,
                price,
                category,
                archived
        );

        try {
            DAO.save(createdProduct);
        } catch (SQLException err) {
            err.printStackTrace();
            return null;
        }

        return createdProduct;
    }  return null;
} */
   
 




