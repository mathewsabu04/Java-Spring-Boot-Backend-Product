package com.example.FinalExamProject.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Setter
public class ProductRequest {
    private String name;
    private String description;
    private String manufacturer;
    private Double price;
    private String category;
    private String region;

    public ProductRequest(String name, String description, String manufacturer, Double price, String category, String region) {
        this.name = name;
        this.description = description;
        this.manufacturer = manufacturer;
        this.price = price;
        this.category = category;
        this.region = region;
    }
}
