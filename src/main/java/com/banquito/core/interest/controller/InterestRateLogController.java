package com.banquito.core.interest.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.interest.model.InterestRateLog;
import com.banquito.core.interest.service.InterestRateLogService;

@RestController
@RequestMapping(path = "/interestLogs")
public class InterestRateLogController {
    private final InterestRateLogService service;

    public InterestRateLogController(InterestRateLogService service) {
        this.service = service;
    }
    @GetMapping("/{id}")
    public ResponseEntity<InterestRateLog> getById(@PathVariable Integer id){
        try {
            return ResponseEntity.ok(this.service.obtainInterestById(id));
        } catch (RuntimeException rte) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/{state}")
    public ResponseEntity<List<InterestRateLog>> getInterestByState(@PathVariable String state){
        return ResponseEntity.ok(this.service.obtainInterestByState(state));
    }
}
