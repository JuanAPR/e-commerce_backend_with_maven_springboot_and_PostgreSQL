package com.prosigmaka.backendjavafinal.repository;

import com.prosigmaka.backendjavafinal.entity.categories.CategoriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepo extends JpaRepository<CategoriesEntity, Integer> {
}
