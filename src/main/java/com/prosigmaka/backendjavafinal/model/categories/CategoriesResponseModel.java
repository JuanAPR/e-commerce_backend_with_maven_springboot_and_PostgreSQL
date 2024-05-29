package com.prosigmaka.backendjavafinal.model.categories;

import com.prosigmaka.backendjavafinal.model.product.ProductsResponseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoriesResponseModel {
    private int id;
    private String name;
    //private List<ProductsResponseModel> products;
}
