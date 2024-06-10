package com.banquito.core.interest.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banquito.core.interest.model.InterestRateLog;

@Repository
public interface InterestRateLogRepository extends JpaRepository<InterestRateLog, Integer> {

    List<InterestRateLog> findByCodeInterestRate(String codeInterestRate);

    List<InterestRateLog> findByState(String state);

    List<InterestRateLog> findByStartDateBetween(Date startDate, Date endDate);
}
