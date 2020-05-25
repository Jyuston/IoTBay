
package uts.isd.controller;

import java.sql.SQLException;
import uts.isd.model.Account;
import uts.isd.model.Product;
import uts.isd.model.dao.DAO;
import uts.isd.model.dao.ProductDAO;

public class CatalogueController {
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

