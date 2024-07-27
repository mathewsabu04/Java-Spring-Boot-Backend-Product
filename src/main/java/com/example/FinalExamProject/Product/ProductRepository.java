package com.example.FinalExamProject.Product;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    @Query("SELECT p FROM Product p WHERE " +
            "(:nameOrDescription is null or :nameOrDescription = '' or p.name like %:nameOrDescription% or p.description like %:nameOrDescription%) and " +
            "(:region is null or p.region = :region) and " +
            "(:category is null or :category = '' or p.category.value = :category)")
    List<Product> findByNameOrDescriptionAndRegionAndCategory(
            String nameOrDescription,
            Region region,
            String category,
            Sort sort
    );


}
