package com.example.FinalExamProject.CommandHandler;

import com.example.FinalExamProject.Category.CategoryRepository;
import com.example.FinalExamProject.Command;
import com.example.FinalExamProject.Product.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateProductCommandHandler implements Command<ProductRequest, ProductDTO> {
    private Logger logger = LoggerFactory.getLogger(CreateProductCommandHandler.class);

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public ResponseEntity<ProductDTO> executes(ProductRequest productRequest)
    {

        logger.info("Creating Product Command Handler " + productRequest + " " + getClass().getSimpleName() );
        // Product Validator
       Product validatedProduct =  ProductValidator.executes(productRequest,categoryRepository.findAll());


        productRepository.save(validatedProduct);
        return ResponseEntity.ok(new ProductDTO(validatedProduct));

    }
}
