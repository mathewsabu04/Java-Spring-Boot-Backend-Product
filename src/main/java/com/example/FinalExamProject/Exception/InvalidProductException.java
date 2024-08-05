package com.example.FinalExamProject.Exception;

import com.example.FinalExamProject.Product.ProductRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

public class InvalidProductException extends CustomBaseException{
    private final Logger logger  = LoggerFactory.getLogger(InvalidProductException.class);
    public InvalidProductException(SimpleResponse simpleResponse, ProductRequest request) {
        super(HttpStatus.BAD_REQUEST,simpleResponse);
        logger.error("Product not valid Exception thrown " + request + " " + simpleResponse + " " + getClass().getSimpleName());
    }
}
