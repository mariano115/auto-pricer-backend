package com.zengroup.autopricerbackend.repository;

import com.zengroup.autopricerbackend.model.Preparation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreparationRepository extends JpaRepository<Preparation, Long> {}
