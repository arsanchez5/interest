package com.banquito.core.interest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.banquito.core.interest.controller.dto.InterestRateDTO;
import com.banquito.core.interest.model.InterestRate;
import com.banquito.core.interest.repository.InterestRateRepository;
import com.banquito.core.interest.util.mapper.InterestRateMapper;

@Service
public class InterestRateService {
    private final InterestRateRepository interestRateRepository;
    private final InterestRateMapper interestRateMapper;

    public InterestRateService(InterestRateRepository interestRateRepository, InterestRateMapper interestRateMapper) {
        this.interestRateRepository = interestRateRepository;
        this.interestRateMapper = interestRateMapper;
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

    public InterestRateDTO createInterestRate(InterestRateDTO interestRateDTO) {
        if (interestRateDTO.getDaysInMonth() <0 || interestRateDTO.getDaysInMonth() > 31){
            throw new RuntimeException("El número de días en el mes debe estar entre 0 y 31");
        }
        if (interestRateDTO.getDaysInYear() <0 || interestRateDTO.getDaysInYear() > 366){
            throw new RuntimeException("El número de días en el año debe estar entre 0 y 366");
        }
        InterestRate interestRate = this.interestRateMapper.toPersistence(interestRateDTO);
        InterestRate savedInterestRate = this.interestRateRepository.save(interestRate);
        return this.interestRateMapper.toDTO(savedInterestRate);
    }

    public InterestRateDTO updateInterestRate(String code, InterestRateDTO interestRateDTO) {
        Optional<InterestRate> interestOpt = this.interestRateRepository.findById(code);
        if (!interestOpt.isPresent()) {
            throw new RuntimeException("No existe el interés con id: " + code);
        }

        if (!code.equals(interestRateDTO.getCode())) {
            throw new RuntimeException("El código en el DTO no coincide con el código proporcionado");
        }

        InterestRate interestRate = interestOpt.get();
        interestRate.setName(interestRateDTO.getName());
        interestRate.setType(interestRateDTO.getType());
        interestRate.setDaysInMonth(interestRateDTO.getDaysInMonth());
        interestRate.setDaysInYear(interestRateDTO.getDaysInYear());

        InterestRate updatedInterestRate = this.interestRateRepository.save(interestRate);
        return this.interestRateMapper.toDTO(updatedInterestRate);
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

    public List<InterestRate> obtainByDaysInMonthAndDaysInYear(Integer daysInMonth, Integer dayInYear){
        return this.interestRateRepository.findByDaysInMonthAndDaysInYear(daysInMonth, dayInYear);
    }

}
