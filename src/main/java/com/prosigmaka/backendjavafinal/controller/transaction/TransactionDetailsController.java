package com.prosigmaka.backendjavafinal.controller.transaction;

import com.prosigmaka.backendjavafinal.helper.GlobalHttpResponse;
import com.prosigmaka.backendjavafinal.model.transaction.TransactionDetailsResponseModel;
import com.prosigmaka.backendjavafinal.service.transaction.TransactionDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pos/api")
@RequiredArgsConstructor
public class TransactionDetailsController {
    private final TransactionDetailsService service;

    @GetMapping("/listtransaksidetail/{id}")
    public ResponseEntity<GlobalHttpResponse<TransactionDetailsResponseModel>> getDetailsTransaction(@PathVariable("id") int id){
        var entity = service.transactionDetails(id);
        return new ResponseEntity<>(entity, HttpStatusCode.valueOf(entity.getStatus()));
    }
}
