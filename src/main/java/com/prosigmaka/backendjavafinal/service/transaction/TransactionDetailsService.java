package com.prosigmaka.backendjavafinal.service.transaction;

import com.prosigmaka.backendjavafinal.entity.transactions.TransactionDetailsEntity;
import com.prosigmaka.backendjavafinal.helper.GlobalHttpResponse;
import com.prosigmaka.backendjavafinal.model.transaction.TransactionDetailsResponseModel;
import com.prosigmaka.backendjavafinal.model.transaction.TransactionsResponseModel;
import com.prosigmaka.backendjavafinal.repository.TransactionDetailsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionDetailsService {
    private final TransactionDetailsRepo transactionDetailsRepo;

    public GlobalHttpResponse<TransactionDetailsResponseModel> transactionDetails(int id){
        try{
            Optional<TransactionDetailsEntity> entity = transactionDetailsRepo.findById(id);
            if(entity.isEmpty()){
                return new GlobalHttpResponse<>(404,"Data not found",new TransactionDetailsEntity().entityToModel());
            } else{
                TransactionDetailsResponseModel model = entity.get().entityToModel();
                return new GlobalHttpResponse<>(200,"Success",model);
            }
        } catch (Exception e){
            return new GlobalHttpResponse<>(400,"Error",null);
        }
    }
}
