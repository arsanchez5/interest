package com.banquito.core.interest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.banquito.core.interest.controller.dto.InterestRateDTO;
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

    public InterestRate createInterestRate(InterestRateDTO interestRateDTO) {
        InterestRate interestRate = new InterestRate();
        interestRate.setCode(interestRateDTO.getCode());
        interestRate.setName(interestRateDTO.getName());
        interestRate.setType(interestRateDTO.getType());
        interestRate.setDaysInMonth(interestRateDTO.getDaysInMonth());
        interestRate.setDaysInYear(interestRateDTO.getDaysInYear());
        return this.interestRateRepository.save(interestRate);
    }

    public InterestRate updateInterestRate(String code, InterestRateDTO interestRateDTO) {
        Optional<InterestRate> interestOpt = this.interestRateRepository.findById(code);
        if (!interestOpt.isPresent()) {
            throw new RuntimeException("No existe el interes con id: " + code);
        }
        InterestRate existingInterest = interestOpt.get();
        existingInterest.setName(interestRateDTO.getName());
        existingInterest.setType(interestRateDTO.getType());
        existingInterest.setDaysInMonth(interestRateDTO.getDaysInMonth());
        existingInterest.setDaysInYear(interestRateDTO.getDaysInYear());
        
        return this.interestRateRepository.save(existingInterest);
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
