package uts.isd.model.reporting;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;

public class TotalSales extends ReportElement implements Serializable {

    public TotalSales(String elementName, String elementDescription) {
        super(elementName, elementDescription);
        // TODO Auto-generated constructor stub
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
    public Hashtable<String, Double> getTotalSalesByProductCategory(ArrayList<TotalSalesRecord> records) {
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