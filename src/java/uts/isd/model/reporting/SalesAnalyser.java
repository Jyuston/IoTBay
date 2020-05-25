package uts.isd.model.reporting;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Set;

public class SalesAnalyser implements Serializable {

    public void use(ArrayList<OrderLineItem> records) {
        //might be used
    }

    // Calculates the total sales value
    // Takes an ArrayList input of TotalSalesRecord objects
    public double getTotalSalesValue(ArrayList<OrderLineItem> records) {
        double total = 0.00;
    
        // Calculate the sum by iterating through the list
        for (OrderLineItem record : records) {
            total += record.getProductPrice() * record.getQuantityOrdered();
        }

        return total;
    }

    public String getTopCategory(ArrayList<OrderLineItem> records) {
        Hashtable<String, Double> dictionary = getSalesByCategory(records);

        String topCategory = "";
        Double salesRevenue = 0.00;

        Set<String> dictionaryKeys = dictionary.keySet();
            
        for (String key : dictionaryKeys) {
            if (dictionary.get(key) > salesRevenue) {
                topCategory = key;
                salesRevenue = dictionary.get(key);
            }
            
        }  

        return topCategory;
    }

    public Double getTopCategoryRevenue(String category, ArrayList<OrderLineItem> records) {

        Hashtable<String, Double> dictionary = getSalesByCategory(records);

        return dictionary.get(category);
    }

    public ProductSummary getTopProduct(ArrayList<ProductSummary> snapshots) {
        ArrayList<ProductSummary> productSummaries = getSalesByProduct(snapshots);

        ProductSummary topProduct = productSummaries.get(0);

        for (ProductSummary product : productSummaries) {
            if (product.getUnitsSold() > topProduct.getUnitsSold()) {
                topProduct = product;
            }
        }

        return topProduct;
    }

    // Returns a hashtable object with a breakdown of sales by product category
    // Takes an ArrayList input of TotalSalesRecord objects
    public Hashtable<String, Double> getSalesByCategory(ArrayList<OrderLineItem> records) {
        //Instantiate a hashtable with String keys, and Double values
        Hashtable<String, Double> dictionary = new Hashtable<String, Double>();

        // Iterate over each object in the ArrayList
        for (OrderLineItem record : records) {
            // If the dictionary does not contain the key (i.e. State), insert a new Key-Value pair
            if (!dictionary.containsKey(record.getProductCategory())) {
                dictionary.put(record.getProductCategory(), record.getProductPrice() * record.getQuantityOrdered());
            }

            else {
                // The new value for the key is calculated (i.e. existingValue += productPrice x quanitity)
                double newValue = dictionary.get(record.getProductCategory()) + (record.getProductPrice() * record.getQuantityOrdered());
                dictionary.replace(record.getProductCategory(), dictionary.get(record.getProductCategory()), newValue);
            }
        }

        // Returns the hash table to the caller
        return dictionary;
    }

    // Returns a HashMap of key-value pairs. Key - product category. Value - all products sold in that category.
    public HashMap<String, ArrayList<ProductSummary>> getSalesByCategoryByProduct(ArrayList<ProductSummary> snapshots, ArrayList<String> categories) {
        // Instatiate the HashMap
        HashMap<String, ArrayList<ProductSummary>> dictionary = new HashMap<>();

        // Generate keys
        for (String string : categories) {
            if (!dictionary.containsKey(string)) {
                //add the key
                //G
                dictionary.put(string, new ArrayList<ProductSummary>());
            }

            else {
                //add to its list
                continue;
            }
        }

        ArrayList<ProductSummary> products = getSalesByProduct(snapshots);

        for (ProductSummary product : products) {
            // The key in the dictionary
            String key = product.getProductCategory();

            // Retrieve the list at that key in the dictionary
            ArrayList<ProductSummary> list = dictionary.get(key);
            list.add(product);

            dictionary.put(key, list);
        }

        return dictionary;
    }

    // Gets the units sold for each product, and the revenue from each product
    public ArrayList<ProductSummary> getSalesByProduct(ArrayList<ProductSummary> snapshots) {

        ArrayList<ProductSummary> products = new ArrayList<>();

        for (ProductSummary snapshot : snapshots) {
            // The productID of the snapshot
            String productID = snapshot.getProductID();
            
            
            int index = determineIndex(products, productID);
            // If the index is 0 --> that product is not yet in the list.
            if (index != -1) {
                // The product is already in the list
                ProductSummary lineItem = products.get(index);

                // Increment that products quantity sold, and add to the revenue
                lineItem.addUnitsSold(snapshot.getUnitsSold());
                lineItem.addProductRevenue(snapshot.getProductRevenue());
            }

            // As the product is not in the list, we need to add it
            else {
                // add the snapshot directly to the list
                products.add(snapshot);
            }
        }

        return products;
    }

    private int determineIndex(ArrayList<ProductSummary> items, String productID) {
        // Returns the index of a certain product in the list
        // Returns 0 if the product is not yet in the list
        for (ProductSummary orderLineSnapshot : items) {
            if (orderLineSnapshot.getProductID().equals(productID)) {
                return items.indexOf(orderLineSnapshot);
            }
        }

        return -1;
    }

    // Returns a hashtable object with a breakdown of sales by state
    // Takes an ArrayList input of TotalSalesRecord objects
    public Hashtable<String, Double> getSalesBySate(ArrayList<OrderLineItem> records) {
        //Instantiate a hashtable with String keys, and Double values
        Hashtable<String, Double> dictionary = new Hashtable<String, Double>();

        // Iterate over each object in the ArrayList
        for (OrderLineItem record : records) {
            // If the dictionary does not contain the key (i.e. State), insert a new Key-Value pair
            if (!dictionary.containsKey(record.getState())) {
                dictionary.put(record.getState(), record.getProductPrice() * record.getQuantityOrdered());
            }

            else {
                // The new value for the key is calculated (i.e. existingValue += productPrice x quanitity)
                double newValue = dictionary.get(record.getState()) + (record.getProductPrice() * record.getQuantityOrdered());
                dictionary.replace(record.getState(), dictionary.get(record.getState()), newValue);
            }
        }

        // Returns the hash table to the caller
        return dictionary;
    }

}