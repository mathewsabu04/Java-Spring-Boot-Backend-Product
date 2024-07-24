package com.example.FinalExamProject.QueryHandler;

import com.example.FinalExamProject.Exception.ProductNotFoundException;
import com.example.FinalExamProject.Product.Product;
import com.example.FinalExamProject.Product.ProductDTO;
import com.example.FinalExamProject.Product.ProductRepository;
import com.example.FinalExamProject.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class GetProductByIdQueryHandler implements Query<String, ProductDTO> {
    private final Logger logger = LoggerFactory.getLogger(GetProductByIdQueryHandler.class);


    private ProductRepository productRepository;

    public GetProductByIdQueryHandler(ProductRepository productRepository){
        this.productRepository = productRepository;

    }


    @Override
    public ResponseEntity<ProductDTO> executes(String id)
    {
        logger.info("Get Product By Id  Query Handler " + id);
       Optional<Product> product = productRepository.findById(id);
       if (product.isEmpty())
       {
           throw new ProductNotFoundException();
       }
       else
       {

           return ResponseEntity.ok(new ProductDTO(product.get()));
       }

    }



}
