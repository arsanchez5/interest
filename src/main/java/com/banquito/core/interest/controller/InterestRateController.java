package com.banquito.core.interest.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.interest.controller.dto.InterestRateDTO;
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
    public ResponseEntity<InterestRateDTO> create(@RequestBody InterestRateDTO interestRateDTO) {
        this.service.createInterestRate(interestRateDTO);
        return ResponseEntity.ok(interestRateDTO);
    }

    @PutMapping("/{code}")
    public ResponseEntity<InterestRateDTO> updateInterestRate(@PathVariable String code, @RequestBody InterestRateDTO interestRateDTO) {
        try {
            this.service.updateInterestRate(code, interestRateDTO);
            return ResponseEntity.ok(interestRateDTO);
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
