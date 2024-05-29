package com.prosigmaka.backendjavafinal.repository;

import com.prosigmaka.backendjavafinal.entity.transactions.TransactionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsRepo extends JpaRepository<TransactionsEntity, Integer> {
}
