package com.banquito.core.interest.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.banquito.core.interest.model.InterestRateLog;
import com.banquito.core.interest.repository.InterestRateLogRepository;

@Service
public class InterestRateLogService {
    private final InterestRateLogRepository interestRateLogRepository;

    public InterestRateLogService(InterestRateLogRepository interestRateLogRepository) {
        this.interestRateLogRepository = interestRateLogRepository;
    }

    public InterestRateLog getInterestLogById(Integer id) {
        Optional<InterestRateLog> interestLogOpt = this.interestRateLogRepository.findById(id);
        if (interestLogOpt.isPresent()) {
            return interestLogOpt.get();
        } else {
            throw new RuntimeException("No existe el interes con id: " + id);

        }
    }

    public List<InterestRateLog> getInterestByState(String state) {
        return this.interestRateLogRepository.findByState(state);
    }

    public List<InterestRateLog> getInterestByCodeInterestRate(String codeInterestRate) {
        return this.interestRateLogRepository.findByCodeInterestRate(codeInterestRate);
    }

    public InterestRateLog saveOrUpdateInterestRateLog(InterestRateLog interestRateLog) {
        return interestRateLogRepository.save(interestRateLog);
    }

    public void deleteInterestRateLog(Integer id) {
        interestRateLogRepository.deleteById(id);
    }

    public List<InterestRateLog> getInterestRateLogsByDateRange(Date startDate, Date endDate) {
        return interestRateLogRepository.findByStartDateBetween(startDate, endDate);
    }

    public List<InterestRateLog> getAllInterestRateLogs() {
        return interestRateLogRepository.findAll();
    }
}
