package com.prosigmaka.backendjavafinal.model.transaction;

import com.prosigmaka.backendjavafinal.model.product.ProductsModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDetailsResponseModel {
    private int id; //show id
    private int transactionId; //show transaction id
    private int productId; //show product id
    private String productsName; //show product name
    private int quantity; //show quantity
    private int subtotal; //show subtotal
}
