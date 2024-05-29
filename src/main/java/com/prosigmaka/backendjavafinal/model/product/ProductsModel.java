package com.prosigmaka.backendjavafinal.model.product;

import com.prosigmaka.backendjavafinal.entity.categories.CategoriesEntity;
import com.prosigmaka.backendjavafinal.entity.products.ProductsEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductsModel {
    private String title;
    private String image;
    private int price;
    private int categoryId;

    public ProductsEntity dtoToEntity(){
        ProductsEntity products = new ProductsEntity();
        CategoriesEntity categories = new CategoriesEntity();
        categories.setId(categoryId);

        products.setTitle(title);
        products.setImage(image);
        products.setPrice(price);
        return products;
    }
}
