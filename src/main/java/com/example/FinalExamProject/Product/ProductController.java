package com.example.FinalExamProject.Product;

import com.example.FinalExamProject.CommandHandler.CreateProductCommandHandler;
import com.example.FinalExamProject.CommandHandler.DeleteProductCommandHandler;
import com.example.FinalExamProject.CommandHandler.UpdateProductCommand;
import com.example.FinalExamProject.CommandHandler.UpdateProductCommandHandler;
import com.example.FinalExamProject.QueryHandler.GetProductByIdQueryHandler;
import com.example.FinalExamProject.QueryHandler.GetProductDTOQueryHandler;
import com.example.FinalExamProject.QueryHandler.GetProductsDTOQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@Cacheable("products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private DeleteProductCommandHandler deleteProductCommandHandler;

    @Autowired
    private GetProductByIdQueryHandler getProductByIdQueryHandler;

    @Autowired
    private GetProductDTOQueryHandler getProductDTOQueryHandler;
    @Autowired
    private CreateProductCommandHandler createProductCommandHandler;

    @Autowired
    private UpdateProductCommandHandler updateProductCommandHandler;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productRepository.findAll());

    }

    // Annotates the method as a GET HTTP request handler for the "/search" URL
    @GetMapping("/products/search")
    public ResponseEntity<List<ProductDTO>> searchProductDTOs(
            // Retrieves the value of the "region" header; defaults to "US" if not provided
            @RequestHeader(value = "region", defaultValue = "US") String region,
            // Retrieves the value of the "category" query parameter; optional
            @RequestParam(required = false) String category,
            // Retrieves the value of the "nameOrDescription" query parameter; optional
            @RequestParam(required = false) String nameOrDescription,
            // Retrieves the value of the "orderBy" query parameter; optional
            @RequestParam(required = false) String orderBy
    ) {
        // Calls the getProductDTOQueryHandler to execute a query with the provided parameters
        return getProductDTOQueryHandler.executes(new GetProductsDTOQuery(
                // Converts the region string to a Region enum value
                Region.valueOf(region),
                // Uses the provided category
                category,
                // Uses the provided name or description
                nameOrDescription,
                // Converts the orderBy string to a ProductSortBy enum value
                ProductSortBy.fromValue(orderBy)
        ));
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable String id)
    {
        return getProductByIdQueryHandler.executes(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        return deleteProductCommandHandler.executes(id);
    }


    @PostMapping("/create")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductRequest request)
    {
        return createProductCommandHandler.executes(request);


    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable String id, @RequestBody ProductRequest productRequest)
    {
        return  updateProductCommandHandler.executes(new UpdateProductCommand(id,productRequest));
    }
}
