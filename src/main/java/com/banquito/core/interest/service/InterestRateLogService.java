package com.banquito.core.interest.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.banquito.core.interest.controller.dto.InterestRateLogDTO;
import com.banquito.core.interest.model.InterestRateLog;
import com.banquito.core.interest.repository.InterestRateLogRepository;
import com.banquito.core.interest.util.mapper.InterestRateLogMapper;

@Service
public class InterestRateLogService {
    private final InterestRateLogRepository interestRateLogRepository;
    private final InterestRateLogMapper interestRateLogMapper;
    
    public InterestRateLogService(InterestRateLogRepository interestRateLogRepository,
            InterestRateLogMapper interestRateLogMapper) {
        this.interestRateLogRepository = interestRateLogRepository;
        this.interestRateLogMapper = interestRateLogMapper;
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

    public InterestRateLogDTO createInterestRateLog(InterestRateLogDTO interestRateLogDTO) {
        InterestRateLog interestRateLog = this.interestRateLogMapper.toPersistence(interestRateLogDTO);
        InterestRateLog savedInterestRateLog = this.interestRateLogRepository.save(interestRateLog);
        return this.interestRateLogMapper.toDTO(savedInterestRateLog);
    }

    public List<InterestRateLog> getInterestRateLogsByDateRange(Date startDate, Date endDate) {
        return interestRateLogRepository.findByStartDateBetween(startDate, endDate);
    }

    public List<InterestRateLog> getAllInterestRateLogs() {
        return interestRateLogRepository.findAll();
    }
}
