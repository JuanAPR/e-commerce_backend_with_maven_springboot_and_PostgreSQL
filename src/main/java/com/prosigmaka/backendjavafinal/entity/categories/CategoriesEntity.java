package com.prosigmaka.backendjavafinal.entity.categories;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.prosigmaka.backendjavafinal.entity.products.ProductsEntity;
import com.prosigmaka.backendjavafinal.model.categories.CategoriesResponseModel;
import com.prosigmaka.backendjavafinal.model.product.ProductsResponseModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories", schema = "public", catalog = "POS")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoriesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "name",nullable = false)
    @NotNull(message = "categories name cannot be null")
    @NotEmpty(message = "categories name cannot empty")
    @Size(max = 255, message = "name cannot be longer than 255 character")
    private String name;
    @OneToMany(mappedBy = "categoryId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<ProductsEntity> products;

    public CategoriesResponseModel entityToModel(){
        CategoriesResponseModel categories = new CategoriesResponseModel();
        List<ProductsResponseModel> models = new ArrayList<>();

//        for(ProductsEntity data : this.products){
//            models.add(data.entityToModel());
//        }
        categories.setId(id);
        categories.setName(name);
        //categories.setProducts(models);
        return categories;
    }
}
