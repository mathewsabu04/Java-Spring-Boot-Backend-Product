package com.example.FinalExamProject.Product;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode // overrides the equals method & compares the contents of objects instead of the memory
public class ProductDTO
{
    private String id;
    private String name;
    private String description;
    private String manufacturer;
    private Double price;
    private String category;

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.description = product.getDescription();
    }




}
