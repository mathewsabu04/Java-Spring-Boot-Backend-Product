package com.example.FinalExamProject.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private GetCategoryQueryHandler getCategoryQueryHandler;

    @GetMapping()
    public ResponseEntity<List<String>> getAll()
    {
        return getCategoryQueryHandler.executes(null);

    }
}
