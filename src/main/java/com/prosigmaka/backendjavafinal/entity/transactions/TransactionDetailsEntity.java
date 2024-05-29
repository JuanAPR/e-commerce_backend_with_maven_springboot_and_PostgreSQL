package com.prosigmaka.backendjavafinal.entity.transactions;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.prosigmaka.backendjavafinal.entity.products.ProductsEntity;
import com.prosigmaka.backendjavafinal.model.product.ProductsModel;
import com.prosigmaka.backendjavafinal.model.transaction.TransactionDetailsResponseModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "transaction_details", schema = "public", catalog = "POS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "transaction_id",referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private TransactionsEntity transactionId;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "product_id",referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private ProductsEntity productId;
    @Column(name = "quantity",nullable = false)
    @NotNull(message = "quantity cannot be null")
    @Min(value = 0, message = "value must be greater than or equal zero")
    private int quantity;
    @Column(name = "subtotal",nullable = false)
    @NotNull(message = "quantity cannot be null")
    @Min(value = 0, message = "value must be greater than or equal zero")
    private int subtotal;

    public TransactionDetailsResponseModel entityToModel(){
        TransactionDetailsResponseModel detailsResponseModel = new TransactionDetailsResponseModel();
        //TransactionsModel transactionsModel = new TransactionsModel();
        ProductsModel productsModel = new ProductsModel();
        productsModel.setTitle(productId.getTitle());

        detailsResponseModel.setId(id);
        detailsResponseModel.setTransactionId(transactionId.getId());
        detailsResponseModel.setProductId(productId.getId());
        detailsResponseModel.setProductsName(productId.getTitle());
        detailsResponseModel.setQuantity(quantity);
        detailsResponseModel.setSubtotal(subtotal);
        return detailsResponseModel;
    }
}
