package com.example.FinalExamProject.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private DeleteProductCommandHandler deleteProductCommandHandler;

    @GetMapping
    public List<Product> getProducts()
    {
       return productRepository.findAll();

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        return deleteProductCommandHandler.excutes(id);

    }

}
