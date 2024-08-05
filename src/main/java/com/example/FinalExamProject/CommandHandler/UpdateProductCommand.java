package com.example.FinalExamProject.CommandHandler;

import com.example.FinalExamProject.Product.ProductRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateProductCommand {
    private String id;
    private ProductRequest productRequest;
}
