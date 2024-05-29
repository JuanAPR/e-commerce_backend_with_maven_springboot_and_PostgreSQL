package com.prosigmaka.backendjavafinal.entity.transactions;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.prosigmaka.backendjavafinal.model.transaction.TransactionDetailsResponseModel;
import com.prosigmaka.backendjavafinal.model.transaction.TransactionsResponseModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "transactions", schema = "public", catalog = "POS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "transaction_date")
    private LocalDate transactionDate;
    @Column(name = "total_amount", nullable = false)
    @NotNull(message = "total amount cannot be null")
    @Min(value = 0, message = "value must be greater than or equal zero")
    private int totalAmount;
    @Column(name = "total_pay",nullable = false)
    @NotNull(message = "total pay cannot be null")
    @Min(value = 0, message = "value must be greater than or equal zero")
    private int totalPay;
    @OneToMany(mappedBy = "transactionId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<TransactionDetailsEntity> transactionDetails;

    public TransactionsResponseModel entityToModel(){
        TransactionsResponseModel transactions = new TransactionsResponseModel();
        List<TransactionDetailsResponseModel> models = new ArrayList<>();

//        for(TransactionDetailsEntity data : this.transactionDetails){
//            models.add(data.entityToModel());
//        }
        transactions.setTotalAmount(totalAmount);
        transactions.setTotalPay(totalPay);
//        transactions.setTransactionDetails(models);
        transactions.setTransactionDate(transactionDate);
        return transactions;
    }
}
