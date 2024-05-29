package com.prosigmaka.backendjavafinal.controller.transaction;

import com.prosigmaka.backendjavafinal.helper.GlobalHttpResponse;
import com.prosigmaka.backendjavafinal.model.transaction.TransactionsModel;
import com.prosigmaka.backendjavafinal.model.transaction.TransactionsResponseModel;
import com.prosigmaka.backendjavafinal.service.transaction.TransactionsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pos/api")
@RequiredArgsConstructor
public class TransactionsController {
    private final TransactionsService service;

    @GetMapping("/listtransaksi")
    public ResponseEntity<GlobalHttpResponse<List<TransactionsResponseModel>>> getAllTransactions(){
        var transaction = service.getAllTransactions();
        return new ResponseEntity<>(transaction, HttpStatusCode.valueOf(transaction.getStatus()));
    }
    @PostMapping("/addtransaction")
    public ResponseEntity<GlobalHttpResponse<TransactionsResponseModel>> addTransaction(@Validated @RequestBody TransactionsModel transactionsModel){
        var added = service.addTransactionAlternate(transactionsModel);
        return new ResponseEntity<>(added,HttpStatusCode.valueOf(added.getStatus()));
    }
}
