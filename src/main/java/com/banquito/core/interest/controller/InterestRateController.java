package com.banquito.core.interest.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        return ResponseEntity.ok(this.service.obtainAllInterestRates());
    }

    @GetMapping("/{code}")
    public ResponseEntity<InterestRate> getInterestById(@PathVariable String code) {
        try {
            return ResponseEntity.ok(this.service.obtainInterestById(code));
        } catch (RuntimeException rte) {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/{type}")
    public ResponseEntity<List<InterestRate>> getInterestByType(@PathVariable String type) {
        return ResponseEntity.ok(this.service.obtainInterestByType(type));
    }
}
