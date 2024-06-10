package com.banquito.core.interest.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.interest.model.InterestRate;
import com.banquito.core.interest.service.InterestRateService;

@RestController
@RequestMapping(path = "interests")
public class InterestRateController {
    private final InterestRateService service;

    public InterestRateController(InterestRateService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<InterestRate>> getAll() {
        return ResponseEntity.ok(this.service.getAllInterestRates());
    }

    @GetMapping("/{code}")
    public ResponseEntity<InterestRate> getInterestById(@PathVariable String code) {
        try {
            return ResponseEntity.ok(this.service.getInterestById(code));
        } catch (RuntimeException rte) {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/name")
    public ResponseEntity<InterestRate> getInterestByName(@PathVariable String name) {
        try {
            return ResponseEntity.ok(this.service.getInterestByName(name));
        } catch (RuntimeException rte) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/type")
    public ResponseEntity<List<InterestRate>> getInterestByType(@PathVariable String type) {
        return ResponseEntity.ok(this.service.getInterestByType(type));
    }

    @PostMapping
    public ResponseEntity<InterestRate> createInterestRate(@RequestBody InterestRate interestRate) {
        return ResponseEntity.ok(this.service.saveOrUpdateInterestRate(interestRate));
    }

    @PostMapping("/{code}")
    public ResponseEntity<InterestRate> updateInterestRate(@PathVariable String code,
            @RequestBody InterestRate interestRate) {
        try {
            InterestRate interest = this.service.getInterestById(code);
            interest.setName(interestRate.getName());
            interest.setType(interestRate.getType());
            interest.setDaysInMonth(interestRate.getDaysInMonth());
            interest.setDaysInYear(interestRate.getDaysInYear());
            return ResponseEntity.ok(this.service.saveOrUpdateInterestRate(interest));
        } catch (RuntimeException rte) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteInterestRate(@PathVariable String code) {
        this.service.deleteInterestRate(code);
        return ResponseEntity.noContent().build();
    }
}
