package uts.isd.model.reporting;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Set;

import uts.isd.model.Product;

public class SalesAnalyser implements Serializable {

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

    public ArrayList<ProductSummary> getTopProduct(ArrayList<ProductSummary> snapshots) {
        ProductSummary reference = snapshots.get(0);
        int topQuantity = 0;
        int topOccuranceCount = 0;

        ArrayList<ProductSummary> topProducts = new ArrayList<>();

        for (ProductSummary product : snapshots) {
            if (product.getUnitsSold() > topQuantity) {
                topQuantity = product.getUnitsSold();
                topOccuranceCount = 1;
                reference = product;
            }

            else if (topQuantity == product.getUnitsSold()) {
                topOccuranceCount++;
            }
        }
        
        // Add all products
        if (topOccuranceCount > 1) {
            for (ProductSummary product : snapshots) {
                if (product.getUnitsSold() == topQuantity) {
                    topProducts.add(product);
                }
            }
        }

        else {
            topProducts.add(reference);
        }
        
        return topProducts;
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
                dictionary.put(string, new ArrayList<ProductSummary>());
            }

            else {
                //add to its list
                continue;
            }
        }

        for (ProductSummary product : snapshots) {
            // The key in the dictionary
            String key = product.getCategory();

            // Retrieve the list at that key in the dictionary
            ArrayList<ProductSummary> list = dictionary.get(key);
            list.add(product);

            dictionary.put(key, list);
        }

        return dictionary;
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

    public HashMap<String, ArrayList<ProductSummary>> getStockReport(ArrayList<ProductSummary> products, ArrayList<String> categories) {
        // Instatiate the HashMap
        HashMap<String, ArrayList<ProductSummary>> dictionary = new HashMap<>();

        // Generate keys
        for (String string : categories) {
            if (!dictionary.containsKey(string)) {
                //add the key
                dictionary.put(string, new ArrayList<ProductSummary>());
            }

            else {
                //add to its list
                continue;
            }
        }

        // Organise each product into the correct hashmap key
        for (ProductSummary product : products) {
            ArrayList<ProductSummary> array = dictionary.get(product.getCategory());
            array.add(product);
        }

        return dictionary;
    }

}