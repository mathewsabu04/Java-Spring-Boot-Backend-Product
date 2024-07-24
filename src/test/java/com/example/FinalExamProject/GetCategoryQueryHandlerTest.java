package com.example.FinalExamProject;

import com.example.FinalExamProject.Category.Category;
import com.example.FinalExamProject.Category.CategoryRepository;
import com.example.FinalExamProject.QueryHandler.GetCategoryQueryHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = FinalExamProjectApplication.class)
public class GetCategoryQueryHandlerTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private GetCategoryQueryHandler getCategoryQueryHandler;

    @BeforeEach
    void setup(){
        getCategoryQueryHandler = new GetCategoryQueryHandler(categoryRepository);
    }

    @Test
    public void categoryQueryHandler_returnsList(){
        //Given
        List<Category> categoryList = Arrays.asList(
                new Category("Clothing"),
                new Category( "Electronics"),
                new Category("Furniture"));

        //When
        when(categoryRepository.findAll()).thenReturn(categoryList);

        //Expecting
        List<String> expected = Arrays.asList("Clothing","Electronics","Furniture");
        ResponseEntity<List<String>> actual = getCategoryQueryHandler.executes(null);
        assertEquals(ResponseEntity.ok(expected),actual);




    }

    @Test
    void categoryQueryHandler_returnsEmptyList()
    {
        when(categoryRepository.findAll()).thenReturn(Collections.emptyList());

        //get actual
        ResponseEntity<List<String>> actual = getCategoryQueryHandler.executes(null);
        assertEquals(ResponseEntity.ok(Collections.emptyList()), actual);
    }
}
