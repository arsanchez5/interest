package com.banquito.core.interest.service;

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

    public InterestRateLog obtainInterestById(Integer id){
        Optional<InterestRateLog> interestLogOpt = this.interestRateLogRepository.findById(id);
        if(interestLogOpt.isPresent()){
            return interestLogOpt.get();
        } else {
            throw new RuntimeException("No existe el interes con id: " + id);
        
        }
    }

    public List<InterestRateLog> obtainInterestByState(String state){
        return this.interestRateLogRepository.findByState(state);
    }
}
