package com.prosigmaka.backendjavafinal.model.transaction;

import com.prosigmaka.backendjavafinal.entity.products.ProductsEntity;
import com.prosigmaka.backendjavafinal.entity.transactions.TransactionDetailsEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDetailsModel {
    //private int transactionId;
    private int productId;
    private int quantity;
    private int subtotal;

    public TransactionDetailsEntity dtoToEntity(){
        TransactionDetailsEntity transactionDetails = new TransactionDetailsEntity();
        ProductsEntity products = new ProductsEntity();
        //TransactionsEntity transactions = new TransactionsEntity();
        products.setId(productId);
        //transactions.setId(transactionId);

        transactionDetails.setQuantity(quantity);
        transactionDetails.setSubtotal(subtotal);
        return transactionDetails;
    }
}
