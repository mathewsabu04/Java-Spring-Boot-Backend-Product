package com.example.FinalExamProject.Exception;

public enum ErrorMessage {
    PRODUCT_NOT_FOUND("Product not found"),
    PRODUCT_CANNOT_BE_EMPTY("Product name can not be empty"),
    PRODUCT_PRICE_CANNOT_BE_NEGATIVE("Product can not be empty or negative "),
    PRODUCT_CATEGORY_IS_NOT_AVAILABLE("Product category is not available"),
    PRODUCT_REGION_IS_NOT_AVAILABLE("Product region is not "),
    PRODUCT_HAS_PROFANITY("Product cannot be saved due to explict keywords"),
    PROFANITY_FILTER_DOWN("Profanity Filter external service is down");




    private  final String message;

    ErrorMessage(String message){
        this.message = message;

    }

    public String getMessage() {
        return message;
    }
}
