package com.zengroup.autopricerbackend.repository;

import com.zengroup.autopricerbackend.model.IngredientAmount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientAmountRepository extends JpaRepository<IngredientAmount, Integer> {}
