package com.example.FinalExamProject;

import com.example.FinalExamProject.Category.CategoryRepository;
import com.example.FinalExamProject.Category.GetCategoryQueryHandler;
import com.example.FinalExamProject.Product.DeleteProductCommandHandler;
import com.example.FinalExamProject.Product.Product;
import com.example.FinalExamProject.Product.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = FinalExamProjectApplication.class)
public class DeleteCategoryQueryHandlerTest {
    @Mock
    private ProductRepository productRepository;

    private DeleteProductCommandHandler deleteProductCommandHandler;

    @BeforeEach
    void setup()
    {
        deleteProductCommandHandler= new DeleteProductCommandHandler(productRepository);

    }


    //test 1 : deletes successfully
    @Test
    public void deleteProductCommandHandler_returnsSuccess()
    {
        String id = "1"; // create a product with id of 1
        Product product = new Product();
        product.setId(id);

        //when we find the product by id, we want to return an optional of the product
        when(productRepository.findById(id)).thenReturn(Optional.of(product));
        ResponseEntity responseEntity = deleteProductCommandHandler.excutes(id);
        verify(productRepository).delete(product);
        assertEquals(responseEntity.getStatusCode(),HttpStatus.OK);
    }




    //test 2: product not found, and send message to UI

    @Test
    public void deleteProductCommandHandler_throwsProductNotFoundException(){

    }
}
