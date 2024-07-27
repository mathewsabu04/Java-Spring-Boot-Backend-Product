package com.example.FinalExamProject.Product;

import com.example.FinalExamProject.Category.Category;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode // overrides the equals method & compares the contents of objects instead of the memory
public class ProductDTO {
    private String id;
    private String name;
    private Category category;
    private String description;
    private String manufacturer;
    private Double price;
    // Getters and setters


    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.category = product.getCategory();
        this.price = product.getPrice();
        this.manufacturer = product.getManufacturer();
        this.description = product.getDescription();
    }



}






