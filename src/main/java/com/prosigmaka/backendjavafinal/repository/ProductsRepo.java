package com.prosigmaka.backendjavafinal.repository;

import com.prosigmaka.backendjavafinal.entity.products.ProductsEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepo extends JpaRepository<ProductsEntity,Integer> {
    List<ProductsEntity> findByTitleContainingIgnoreCase(String title, Sort sort);
    List<ProductsEntity> findByCategoryId_Id(Integer id);
}
