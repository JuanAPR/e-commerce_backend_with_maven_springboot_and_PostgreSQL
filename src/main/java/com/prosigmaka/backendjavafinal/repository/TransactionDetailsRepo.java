package com.prosigmaka.backendjavafinal.repository;

import com.prosigmaka.backendjavafinal.entity.transactions.TransactionDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionDetailsRepo extends JpaRepository<TransactionDetailsEntity, Integer> {
}
