package com.example.FinalExamProject;

import com.example.FinalExamProject.Category.Category;
import com.example.FinalExamProject.Product.Product;
import com.example.FinalExamProject.Product.ProductDTO;
import com.example.FinalExamProject.Product.ProductRepository;
import com.example.FinalExamProject.Product.ProductSortBy;
import com.example.FinalExamProject.QueryHandler.GetProductDTOQueryHandler;
import com.example.FinalExamProject.QueryHandler.GetProductsDTOQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = FinalExamProjectApplication.class)
public class GetProductDTOQueryHandlerTest {
    @Mock
    private ProductRepository productRepository;

    private GetProductDTOQueryHandler getProductDTOQueryHandler;

    @BeforeEach
    void setUp()
    {
        getProductDTOQueryHandler = new GetProductDTOQueryHandler(productRepository);
    }

    @Test
    void testGetProductDTOQueryHandler_returnsEmptyList()
    {
        when(productRepository.findByNameOrDescriptionAndRegionAndCategory(null,null,null,null)).thenReturn(Collections.emptyList());

        ResponseEntity<List<ProductDTO>> responseEntity = getProductDTOQueryHandler.executes(new GetProductsDTOQuery
                (null,null,null,null));
        assertEquals(responseEntity.getBody(),Collections.emptyList());
    }

    @Test
    void testGetProductDTOQueryHandler_returnsList(){
        when(productRepository.findByNameOrDescriptionAndRegionAndCategory(any(),any(),any(),any()))
                .thenReturn(getProducts());

        ResponseEntity<List<ProductDTO>> responseEntity = getProductDTOQueryHandler.executes(new GetProductsDTOQuery
                (null,null,null,null));

        List<ProductDTO> actualResponse = responseEntity.getBody();
        assertEquals(2,actualResponse.size());
    }

    @Test
    void testDefineSort_returnsUnsorted(){
        Sort sort = getProductDTOQueryHandler.defineSort(null);
        assertEquals(Sort.unsorted(),sort);
    }

    @Test
    void testDefineSort_returnsSorted()
    {
        Sort sort  = getProductDTOQueryHandler.defineSort(ProductSortBy.NAME);
        assertEquals(Sort.by(Sort.Direction.ASC,"name"), sort );
    }

    private List<Product> getProducts()
    {
        Product product = new Product();
        product.setId("1");
        product.setCategory(new Category("Clothing"));

        Product product2 = new Product();
        product2.setId("2");
        product2.setCategory(new Category("Electronics"));
        return Arrays.asList(product,product2);
    }
}
