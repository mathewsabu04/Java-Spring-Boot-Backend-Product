package com.example.FinalExamProject.QueryHandler;

import com.example.FinalExamProject.Exception.ProductNotFoundException;
import com.example.FinalExamProject.Product.Product;
import com.example.FinalExamProject.Product.ProductDTO;
import com.example.FinalExamProject.Product.ProductRepository;
import com.example.FinalExamProject.Product.ProductSortBy;
import com.example.FinalExamProject.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetProductDTOQueryHandler implements Query<GetProductsDTOQuery, List<ProductDTO>> {
    @Autowired
    private ProductRepository productRepository;

    public GetProductDTOQueryHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<List<ProductDTO>> executes(GetProductsDTOQuery query) {
        final Logger logger = LoggerFactory.getLogger(GetProductDTOQueryHandler.class); // logger

        Sort productSort = defineSort(query.getProductSortBy()); // sort the products

        List<Product> products = productRepository
                .findByNameOrDescriptionAndRegionAndCategory(
                        query.getNameOrDescription(),
                        query.getRegion(),
                        query.getCategory(),
                        productSort

                );

//        if (products.isEmpty()){
//            throw new ProductNotFoundException();
//        }
        products.forEach(product -> logger.info("Get name: " + product.getName()));

        return ResponseEntity.ok(products.stream().map(ProductDTO::new).collect(Collectors.toList()));
    }

    public Sort defineSort(ProductSortBy productSortBy) {
        if (productSortBy == null) {
            return Sort.unsorted();
        }

        switch (productSortBy) {
            case NAME:
                return Sort.by(Sort.Order.asc("name"));
            case PRICE:
                return Sort.by(Sort.Order.asc("price"));
            default:
                return Sort.unsorted();
        }
    }

}
