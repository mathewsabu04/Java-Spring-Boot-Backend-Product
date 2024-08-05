package com.example.FinalExamProject.CommandHandler;

import com.example.FinalExamProject.Command;
import com.example.FinalExamProject.Exception.ProductNotFoundException;
import com.example.FinalExamProject.Product.Product;
import com.example.FinalExamProject.Product.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteProductCommandHandler implements Command<String,Void> {


    public ProductRepository productRepository;
    private Logger logger = LoggerFactory.getLogger(DeleteProductCommandHandler.class);

    public DeleteProductCommandHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<Void>executes(String id) {
        logger.info("Delete Product Command Handler, id: " + id + " " + getClass().getSimpleName());

        Optional<Product> product = productRepository.findById(id);
        // see if the product is present
        if(product.isEmpty())
        {
            throw new ProductNotFoundException();


        }
        productRepository.delete(product.get());
        System.out.println("Product has been deleted");
        return ResponseEntity.ok().build();

    }
}
