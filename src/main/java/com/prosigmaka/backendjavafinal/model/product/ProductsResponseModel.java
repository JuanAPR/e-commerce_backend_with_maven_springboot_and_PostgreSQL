package com.prosigmaka.backendjavafinal.model.product;

import com.prosigmaka.backendjavafinal.model.categories.CategoriesModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductsResponseModel {
    private int id; //to show id
    private String title; //to show title
    private String image; //to show image
    private int price; //to show price
    private String categoriesName; //to show name of categories
    private int categoryId; //to show categories id of products
}
