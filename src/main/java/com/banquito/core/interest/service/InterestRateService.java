package com.banquito.core.interest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.banquito.core.interest.model.InterestRate;
import com.banquito.core.interest.repository.InterestRateRepository;

@Service
public class InterestRateService {
    private final InterestRateRepository interestRateRepository;

    public InterestRateService(InterestRateRepository interestRateRepository) {
        this.interestRateRepository = interestRateRepository;
    }

    public InterestRate getInterestById(String code) {
        Optional<InterestRate> interestOpt = this.interestRateRepository.findById(code);
        if (interestOpt.isPresent()) {
            return interestOpt.get();
        } else {
            throw new RuntimeException("No existe el interes con id: " + code);
        }
    }

    public InterestRate getInterestByName(String name) {
        Optional<InterestRate> interestOpt = this.interestRateRepository.findByName(name);
        if (interestOpt.isPresent()) {
            return interestOpt.get();
        } else {
            throw new RuntimeException("No existe el interes con nombre:" + name);
        }
    }

    public InterestRate saveOrUpdateInterestRate(InterestRate interestRate) {
        return this.interestRateRepository.save(interestRate);
    }

    public List<InterestRate> getAllInterestRates() {
        return this.interestRateRepository.findAll();
    }

    public List<InterestRate> getInterestByType(String type) {
        return this.interestRateRepository.findByType(type);
    }

    public void deleteInterestRate(String code) {
        this.interestRateRepository.deleteById(code);
    }

}
