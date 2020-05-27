
package uts.isd.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import uts.isd.model.Account;
import uts.isd.model.Product;
import uts.isd.model.dao.DAO;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.ProductDAO;

public class CatalogueController extends HttpServlet {
   private DBConnector db;
    private Connection conn;
    private ProductDAO ProductDAO;
    
@Override
    public void init() {
        try {
            db = new DBConnector();
            conn = db.openConnection();
            productDAO = new productDAO(conn);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CatalogueController.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        doGet(request, response);
    }
    
    
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        

    
    
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
}
   
 
}



