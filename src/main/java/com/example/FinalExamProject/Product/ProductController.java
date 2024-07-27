package com.example.FinalExamProject.Product;

import com.example.FinalExamProject.Category.Category;
import com.example.FinalExamProject.CommandHandler.DeleteProductCommandHandler;
import com.example.FinalExamProject.QueryHandler.GetProductByIdQueryHandler;
import com.example.FinalExamProject.QueryHandler.GetProductDTOQueryHandler;
import com.example.FinalExamProject.QueryHandler.GetProductsDTOQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private DeleteProductCommandHandler deleteProductCommandHandler;

    @Autowired
    private GetProductByIdQueryHandler getProductByIdQueryHandler;

    @Autowired
    private GetProductDTOQueryHandler getProductDTOQueryHandler;

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productRepository.findAll());
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductDTO>> searchProductDTOs(
            @RequestHeader(value = "region", defaultValue = "US") String region,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String nameOrDescription,
            @RequestParam(required = false) String orderBy
    ) {

        return getProductDTOQueryHandler.executes(new GetProductsDTOQuery(
                Region.valueOf(region),
                category,
                nameOrDescription,
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
        return deleteProductCommandHandler.excutes(id);
    }
}
