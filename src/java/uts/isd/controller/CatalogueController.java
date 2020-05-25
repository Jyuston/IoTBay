
package uts.isd.controller;

import uts.isd.model.Product;

public class CatalogueController {
    public static Product registerProduct(String name, int stock, double price, String category, boolean archived) {
        // validateInfo() // Example

        Product createdProduct = Product.create(name, stock, price, category, archived);

        // redundant but shows that u can do checks in the controller
        if (createdProduct == null)
            System.err.println("Create product failed");

        return createdProduct;
    }
 
}

