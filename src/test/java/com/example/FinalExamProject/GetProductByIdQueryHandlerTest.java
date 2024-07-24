package com.example.FinalExamProject;

import com.example.FinalExamProject.Category.Category;
import com.example.FinalExamProject.Exception.ProductNotFoundException;
import com.example.FinalExamProject.Product.Product;
import com.example.FinalExamProject.Product.ProductDTO;
import com.example.FinalExamProject.Product.ProductRepository;
import com.example.FinalExamProject.QueryHandler.GetProductByIdQueryHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = FinalExamProjectApplication.class)
public class GetProductByIdQueryHandlerTest {

    @Mock
    private ProductRepository productRepository;

    private GetProductByIdQueryHandler getProductByIdQueryHandler;


    //querey Handler


    @BeforeEach
    void setup()
    {
        //TODO
        getProductByIdQueryHandler = new GetProductByIdQueryHandler(productRepository);
    }

    @Test
    public void getProductByIdQueryHandler_returnsSuccess()
    {
        //create product with id
        String id = "1";

        Product product = new Product();
        product.setId(id);
        product.setCategory(new Category("Electronics"));

        //when we find the product by id, we want to return a optional of the product

        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        ResponseEntity<ProductDTO> responseEntity = getProductByIdQueryHandler.executes(id);
        verify(productRepository).findById(id);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

        assertEquals(responseEntity.getBody(),new ProductDTO(product));


    }

    @Test
    //test 2 : if user puts a id and can't find it, throw product not found exception
    public void getProductByIdQueryHandler_throwsProductNotFoundException()
    {
        //create product with id
        String id = "1";

        Product product = new Product();
        product.setId(id);
        product.setCategory(new Category("Electronics"));

        when(productRepository.findById(id)).thenReturn(Optional.empty());

        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class,() -> getProductByIdQueryHandler.executes(id));

        assertEquals("Product not found",exception.getSimpleResponse().getMessage());

        //validation


    }





}
