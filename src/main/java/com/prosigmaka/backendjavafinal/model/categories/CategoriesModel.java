package com.prosigmaka.backendjavafinal.model.categories;

import com.prosigmaka.backendjavafinal.entity.categories.CategoriesEntity;
import com.prosigmaka.backendjavafinal.entity.products.ProductsEntity;
import com.prosigmaka.backendjavafinal.model.product.ProductsModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoriesModel {
    private String name;
    //private List<ProductsModel> productsModels;

    public CategoriesEntity dtoToEntity(){
        CategoriesEntity categories = new CategoriesEntity();
        categories.setName(name);
        return categories;
    }
}
