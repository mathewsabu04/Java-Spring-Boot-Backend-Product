package com.example.FinalExamProject.CommandHandler;

import com.example.FinalExamProject.Category.CategoryRepository;
import com.example.FinalExamProject.Command;
import com.example.FinalExamProject.Exception.ProductNotFoundException;
import com.example.FinalExamProject.Product.Product;
import com.example.FinalExamProject.Product.ProductDTO;
import com.example.FinalExamProject.Product.ProductRepository;
import com.example.FinalExamProject.Product.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UpdateProductCommandHandler implements Command<UpdateProductCommand, ProductDTO> {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;

    private final ProductValidator productValidator;

    public UpdateProductCommandHandler(ProductValidator productValidator) {
        this.productValidator = productValidator;
    }

    public ResponseEntity<ProductDTO> executes(UpdateProductCommand command) {
        //find the product by id
        Optional<Product> product = productRepository.findById(command.getId());
        if (product.isEmpty()){
            throw  new ProductNotFoundException();
        }
        else{
            //check if it's valid
            Product validatedProduct = productValidator.executes(command.getProductRequest(), categoryRepository.findAll());
            validatedProduct.setId(product.get().getId());
            validatedProduct.setCreatedAt(product.get().getCreatedAt());
            productRepository.save(validatedProduct);
            return ResponseEntity.ok(new ProductDTO(validatedProduct));
        }
    }
}
