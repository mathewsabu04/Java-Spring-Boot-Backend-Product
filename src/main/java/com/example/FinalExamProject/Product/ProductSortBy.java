// Define the package for the enum
package com.example.FinalExamProject.Product;

// Define an enum named ProductSortBy
public enum ProductSortBy {
    // Define enum constants with corresponding string values
    NAME("name"),
    PRICE("price");

    // Declare a private final field to hold the string value of the enum constant
    private final String value;

    // Constructor to initialize the enum constant with the specified string value
    ProductSortBy(String value) {
        this.value = value;
    }

    // Static method to get a ProductSortBy enum constant from a string value
    public static ProductSortBy fromValue(String value) {
        // Loop through all enum constants
        for (ProductSortBy sortBy : ProductSortBy.values()) {
            // Check if the value of the current enum constant matches the input value (case-insensitive)
            if (sortBy.value.equalsIgnoreCase(value)) {
                // Return the matching enum constant
                return sortBy;
            }
        }
        // Return null if no matching enum constant is found
        return null;
    }

    // Getter method to return the string value of the enum constant
    public String getValue() {
        return this.value;
    }
}
