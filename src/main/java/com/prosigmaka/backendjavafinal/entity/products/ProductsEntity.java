package com.prosigmaka.backendjavafinal.entity.products;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.prosigmaka.backendjavafinal.entity.categories.CategoriesEntity;
import com.prosigmaka.backendjavafinal.entity.transactions.TransactionDetailsEntity;
import com.prosigmaka.backendjavafinal.model.categories.CategoriesModel;
import com.prosigmaka.backendjavafinal.model.product.ProductsResponseModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "products", schema = "public", catalog = "POS")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private int id;
    @Column(name = "title",nullable = false)
    @NotEmpty(message = "title cannot be empty")
    @NotNull(message = "title cannot be null")
    @Size(max = 255, message = "title cannot be longer than 255 character")
    private String title;
    @Column(name = "image")
    @Size(max = 255, message = "image cannot be longer than 255 character")
    private String image;
    @Column(name = "price",nullable = false)
    @NotNull(message = "price cannot be null")
    @Min(value = 0, message = "value must be greater than or equal zero")
    private int price;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private CategoriesEntity categoryId;
    @OneToMany(mappedBy = "productId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<TransactionDetailsEntity> transactionDetails;

    public ProductsResponseModel entityToModel(){
        ProductsResponseModel products = new ProductsResponseModel();
        CategoriesModel categories = new CategoriesModel();
        categories.setName(categoryId.getName());

        products.setId(id);
        products.setTitle(title);
        products.setImage(image);
        products.setPrice(price);
        products.setCategoryId(categoryId.getId());
        products.setCategoriesName(categoryId.getName());
        return products;
    }
}
