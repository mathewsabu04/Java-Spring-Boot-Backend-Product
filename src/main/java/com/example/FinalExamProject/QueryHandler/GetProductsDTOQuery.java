package com.example.FinalExamProject.QueryHandler;

import com.example.FinalExamProject.Product.ProductSortBy;
import com.example.FinalExamProject.Product.Region;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class GetProductsDTOQuery {
    private Region region;
    private String category;
    private String nameOrDescription;
    private ProductSortBy productSortBy;

    public GetProductsDTOQuery(Region region, String category, String nameOrDescription, ProductSortBy productSortBy) {
        this.region = region;
        this.category = category;
        this.nameOrDescription = nameOrDescription;
        this.productSortBy = productSortBy;
    }
}
