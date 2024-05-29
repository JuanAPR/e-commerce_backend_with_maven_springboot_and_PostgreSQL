package com.prosigmaka.backendjavafinal.service.transaction;

import com.prosigmaka.backendjavafinal.entity.products.ProductsEntity;
import com.prosigmaka.backendjavafinal.entity.transactions.TransactionDetailsEntity;
import com.prosigmaka.backendjavafinal.entity.transactions.TransactionsEntity;
import com.prosigmaka.backendjavafinal.helper.GlobalHttpResponse;
import com.prosigmaka.backendjavafinal.model.transaction.TransactionDetailsModel;
import com.prosigmaka.backendjavafinal.model.transaction.TransactionDetailsResponseModel;
import com.prosigmaka.backendjavafinal.model.transaction.TransactionsModel;
import com.prosigmaka.backendjavafinal.model.transaction.TransactionsResponseModel;
import com.prosigmaka.backendjavafinal.repository.ProductsRepo;
import com.prosigmaka.backendjavafinal.repository.TransactionDetailsRepo;
import com.prosigmaka.backendjavafinal.repository.TransactionsRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionsService {
    private final TransactionsRepo transactionsRepo;
    private final ProductsRepo productsRepo;
    private final TransactionDetailsRepo transactionDetailsRepo;

    public GlobalHttpResponse<List<TransactionsResponseModel>> getAllTransactions(){

            List<TransactionsEntity> transactions = transactionsRepo.findAll();
            List<TransactionsResponseModel> model = new ArrayList<>();
            for(TransactionsEntity data : transactions){
                model.add(data.entityToModel());
            }
            return new GlobalHttpResponse<>(200,"Success",model);
    }

    /*@Transactional
    public GlobalHttpResponse<TransactionsResponseModel> addTransaction(TransactionsModel transactionsModel){
        if(transactionsModel.getTotalAmount() > transactionsModel.getTotalPay()){
            return new GlobalHttpResponse<>(400,"Total pay must be greater or equal to total amount",null);
        }
        Integer totalAmountFromDetails = transactionsModel.getTransactionsDetails().stream()
                .mapToInt(TransactionDetailsModel::getSubtotal)
                .sum();
        if(!totalAmountFromDetails.equals(transactionsModel.getTotalAmount())){
            return new GlobalHttpResponse<>(400,"Incorrect total amount",null);
        }
        try{ //todo search error in try catch
//            TransactionsEntity transactions = transactionsModel.dtoToEntity();
//            transactionsRepo.save(transactions);
            TransactionsEntity transactions = new TransactionsEntity();
            TransactionDetailsEntity details = new TransactionDetailsEntity();
            transactions.setTransactionDate(LocalDate.now());
            transactions.setTotalAmount(transactionsModel.getTotalAmount());
            transactions.setTotalPay(transactionsModel.getTotalPay());
            transactionsRepo.save(transactions);
            log.info("before iterator");
            for(TransactionDetailsModel data : transactionsModel.getTransactionsDetails()){
                log.info("in iterator");
                ProductsEntity products = productsRepo.findById(data.getProductId()).orElse(null);
                log.info("after check product before if");
                if(products == null){
                    return new GlobalHttpResponse<>(404,"Product not found",null);
                }

                Integer expectedSubtotal = products.getPrice() * data.getQuantity();
                if(!expectedSubtotal.equals(data.getSubtotal())){
                    return new GlobalHttpResponse<>(400,"Subtotal incorrect",null);
                }
                //TransactionDetailsEntity details = data.dtoToEntity();
                details.setTransactionId(transactions);
                details.setQuantity(data.getQuantity());
                details.setSubtotal(data.getSubtotal());
                details.setProductId(products);
                log.info("before save details");
                transactionDetailsRepo.save(details); //insert kedua tidak ter save
                log.info("after save details");
            }
            return new GlobalHttpResponse<>(201,"Success",transactions.entityToModel());
        }catch (NullPointerException e){
            //TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new GlobalHttpResponse<>(400,e.getMessage(),null);
        }
    }*/

    @Transactional
    public GlobalHttpResponse<TransactionsResponseModel> addTransactionAlternate(TransactionsModel transactionsModel){
        if(transactionsModel.getTotalAmount() > transactionsModel.getTotalPay()){
            return new GlobalHttpResponse<>(400,"Total pay must be greater or equal to total amount",null);
        }
        Integer totalAmountFromDetails = transactionsModel.getTransactionsDetails().stream()
                .mapToInt(TransactionDetailsModel::getSubtotal)
                .sum();
        if(!totalAmountFromDetails.equals(transactionsModel.getTotalAmount())){
            return new GlobalHttpResponse<>(400,"Incorrect total amount",null);
        }
        try{
            TransactionsEntity transactions = transactionsModel.dtoToEntity();
            List<TransactionDetailsEntity> entity = new ArrayList<>();
            for(TransactionDetailsModel detail : transactionsModel.getTransactionsDetails()){
                ProductsEntity products = productsRepo.findById(detail.getProductId()).orElse(null);
                if(products == null) return new GlobalHttpResponse<>(404,"Product not found",null);
                Integer expectedSubtotal = products.getPrice() * detail.getQuantity();
                if(!expectedSubtotal.equals(detail.getSubtotal())) return new GlobalHttpResponse<>(400,"Subtotal incorrect",null);

                var transactionDetail = detail.dtoToEntity();
                transactionDetail.setProductId(products);
                entity.add(transactionDetail);
            }
            transactions.setTransactionDetails(entity);
            var tx = transactionsRepo.save(transactions);
            for(var detailTransaction : entity) detailTransaction.setTransactionId(tx);
            transactionDetailsRepo.saveAll(entity);
            return new GlobalHttpResponse<>(201,"Success",null);
        }catch (NullPointerException e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new GlobalHttpResponse<>(400,e.getMessage(),null);
        }
    }
}
