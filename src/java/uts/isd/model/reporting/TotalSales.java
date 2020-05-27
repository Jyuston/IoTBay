package uts.isd.model.reporting;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Set;

public class TotalSales extends ReportElement implements Serializable {

    public TotalSales(String elementName, String elementDescription) {
        super(elementName, elementDescription);
        // TODO Auto-generated constructor stub
        // INIT Commit
    }

    public void use(ArrayList<TotalSalesRecord> records) {
        //might be used
    }

    // Calculates the total sales value
    // Takes an ArrayList input of TotalSalesRecord objects
    public double getTotalSalesValue(ArrayList<TotalSalesRecord> records) {
        double total = 0.00;

        // Calculate the sum by iterating through the list
        for (TotalSalesRecord record : records) {
            total += record.getProductPrice() * record.getQuantityOrdered();
        }

        return total;
    }

    // Returns a hashtable object with a breakdown of sales by product category
    // Takes an ArrayList input of TotalSalesRecord objects
    public Hashtable<String, Double> getTotalSalesByProductCategorySummary(ArrayList<TotalSalesRecord> records) {
        //Instantiate a hashtable with String keys, and Double values
        Hashtable<String, Double> dictionary = new Hashtable<String, Double>();

        // Iterate over each object in the ArrayList
        for (TotalSalesRecord record : records) {
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
    public HashMap<String, ArrayList<OrderLineSnapshot>> getTotalSalesByProductCategory(ArrayList<OrderLineSnapshot> snapshots, ArrayList<String> categories) {
        // Instatiate the HashMap
        HashMap<String, ArrayList<OrderLineSnapshot>> dictionary = new HashMap<>();

        // Generate keys
        for (String string : categories) {
            if (!dictionary.containsKey(string)) {
                //add the key
                //G
                dictionary.put(string, new ArrayList<OrderLineSnapshot>());
            }

            else {
                //add to its list
                continue;
            }
        }

        // Add to each key's value
        for (OrderLineSnapshot snapshot : snapshots) {
            // The key in the dictionary
            String key = snapshot.getProductCategory();

            // The productID of the snapshot
            String productID = snapshot.getProductID();

            // Retrieve the list at that key in the dictionary
            ArrayList<OrderLineSnapshot> list = dictionary.get(key);
            
            // Check if the list is empty
            int index = determineIndex(list, productID);
            // If the index is 0 --> that product is not yet in the list.
            if (index != -1) {
                // The product is already in the list
                OrderLineSnapshot lineItem = list.get(index);

                // Increment that products quantity sold, and add to the revenue
                lineItem.addUnitsSold(snapshot.getUnitsSold());
                lineItem.addProductRevenue(snapshot.getProductRevenue());
            }

            // As the product is not in the list, we need to add it
            else {
                // add the snapshot directly to the list
                list.add(snapshot);
            }
            
            // Save the updates to the dictionary
            dictionary.put(key, list);
        }

        return dictionary;
    }

    private int determineIndex(ArrayList<OrderLineSnapshot> items, String productID) {
        // Returns the index of a certain product in the list
        // Returns 0 if the product is not yet in the list
        for (OrderLineSnapshot orderLineSnapshot : items) {
            if (orderLineSnapshot.getProductID().equals(productID)) {
                return items.indexOf(orderLineSnapshot);
            }
        }

        return -1;
    }

    // Returns a hashtable object with a breakdown of sales by state
    // Takes an ArrayList input of TotalSalesRecord objects
    public Hashtable<String, Double> getTotalSalesByState(ArrayList<TotalSalesRecord> records) {
        //Instantiate a hashtable with String keys, and Double values
        Hashtable<String, Double> dictionary = new Hashtable<String, Double>();

        // Iterate over each object in the ArrayList
        for (TotalSalesRecord record : records) {
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