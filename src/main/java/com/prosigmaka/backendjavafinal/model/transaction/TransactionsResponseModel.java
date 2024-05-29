package com.prosigmaka.backendjavafinal.model.transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionsResponseModel {
    private int totalAmount;
    private int totalPay;
    private LocalDate transactionDate;
    //private List<TransactionDetailsResponseModel> transactionDetails;

}
