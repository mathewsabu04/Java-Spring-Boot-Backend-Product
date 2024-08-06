package com.example.FinalExamProject.Product;

import com.example.FinalExamProject.Category.Category;
import com.example.FinalExamProject.Exception.ErrorMessage;
import com.example.FinalExamProject.Exception.InvalidProductException;
import com.example.FinalExamProject.Exception.ProfanityValidator;
import com.example.FinalExamProject.Exception.SimpleResponse;
import io.micrometer.common.util.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ProductValidator {
    private final ProfanityValidator profanityValidator;

    public ProductValidator(ProfanityValidator profanityValidator) {
        this.profanityValidator = profanityValidator;
    }

    public Product executes(ProductRequest request , List<Category> categoryList)
    {
        // have a bunch of if statements -> each one will call a method to check each attribute.
        if (nameIsEmpty(request.getName()))
        {
            throw new InvalidProductException(new SimpleResponse(ErrorMessage.PRODUCT_CANNOT_BE_EMPTY.getMessage()), request);

        }

        if(priceIsNegative(request.getPrice())){
            throw  new InvalidProductException(new SimpleResponse(ErrorMessage.PRODUCT_PRICE_CANNOT_BE_NEGATIVE.getMessage()),request);

        }

        if(categoryNotAvailable(request.getCategory(),categoryList)){
            throw  new InvalidProductException(new SimpleResponse(ErrorMessage.PRODUCT_CATEGORY_IS_NOT_AVAILABLE.getMessage()),request);

        }
        if(regionNotAvailable(request.getRegion()))
        {
            throw new InvalidProductException(new SimpleResponse(ErrorMessage.PRODUCT_REGION_IS_NOT_AVAILABLE.getMessage()),request);
        }
        // Profanity Validator check -> external api integration
        if(profanityValidator.hasProfanity(request.getName(),request.getDescription()))
        {
            throw new InvalidProductException(new SimpleResponse(ErrorMessage.PRODUCT_HAS_PROFANITY.getMessage()),request);
        }


        //TODO
        return new Product(request);

    }

    private static boolean regionNotAvailable(String region1) {
        for (Region region: Region.values()){
            if(region.name().equals(region1)){
                return false;
            }
        }
        return true;
    }

    private static boolean categoryNotAvailable(String request, List<Category> categoryList) {
        return !categoryList.contains(new Category(request));
    }

    public static boolean nameIsEmpty(String name) {
        return StringUtils.isEmpty(name);
    }

    private static boolean priceIsNegative(Double price) {
        return price != null && price < 0.0;

    }




}
