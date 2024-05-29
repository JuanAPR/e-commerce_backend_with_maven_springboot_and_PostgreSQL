package com.prosigmaka.backendjavafinal.model.transaction;

import com.prosigmaka.backendjavafinal.entity.transactions.TransactionsEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionsModel {
    private int totalAmount;
    private int totalPay;
    private LocalDate transactionDate;
    private List<TransactionDetailsModel> transactionsDetails;

    public TransactionsEntity dtoToEntity(){
        TransactionsEntity transactions = new TransactionsEntity();
        TransactionsResponseModel model = new TransactionsResponseModel();
        transactions.setTotalAmount(totalAmount);
        transactions.setTotalPay(totalPay);
        transactions.setTransactionDate(transactionDate = LocalDate.now());
        //transactions.setTransactionDetails();
        return transactions;
    }
}
