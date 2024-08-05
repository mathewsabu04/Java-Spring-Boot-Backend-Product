package com.example.FinalExamProject.Product;

import com.example.FinalExamProject.Category.Category;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "price")
    private Double price;

    // Category relationship
    @ManyToOne
    @JoinColumn(name = "category_value", referencedColumnName = "value")
    private Category category;

    @Column(name = "region")
    @Enumerated(EnumType.STRING)
    private Region region;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updateAt;

    // Getter for category
    public Category getCategory() {
        return category;
    }

    public String getName()
    {
        return  name;
    }

    // Other getters and setters

    public Product( ProductRequest request){
        this.name = request.getName();
        this.description = request.getDescription();
        this.manufacturer = request.getManufacturer();
        this.price = request.getPrice();
        this.category = new Category(request.getCategory());
        this.region = Region.valueOf(request.getRegion());
    }
}
