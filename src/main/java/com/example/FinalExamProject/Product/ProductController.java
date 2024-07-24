package com.example.FinalExamProject.Product;

import com.example.FinalExamProject.CommandHandler.DeleteProductCommandHandler;
import com.example.FinalExamProject.QueryHandler.GetProductByIdQueryHandler;
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

    @GetMapping
    public List<Product> getProducts()
    {
       return productRepository.findAll();

    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable String id){
        return getProductByIdQueryHandler.executes(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        return deleteProductCommandHandler.excutes(id);

    }

}
