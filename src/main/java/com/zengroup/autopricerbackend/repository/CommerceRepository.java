package com.zengroup.autopricerbackend.repository;

import com.zengroup.autopricerbackend.model.Commerce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommerceRepository extends JpaRepository<Commerce, Integer> {}
