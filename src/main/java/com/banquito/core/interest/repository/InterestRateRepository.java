package com.banquito.core.interest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banquito.core.interest.model.InterestRate;

@Repository
public interface InterestRateRepository extends JpaRepository<InterestRate, String> {

    List<InterestRate> findByType(String type);

}
