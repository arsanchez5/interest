package com.banquito.core.interest.controller;

import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.interest.controller.dto.InterestRateLogDTO;
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
    public ResponseEntity<InterestRateLog> getById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(this.service.getInterestLogById(id));
        } catch (RuntimeException rte) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/state")
    public ResponseEntity<List<InterestRateLog>> getInterestByState(@PathVariable String state) {
        return ResponseEntity.ok(this.service.getInterestByState(state));
    }

    @GetMapping("/codeInterestRate")
    public ResponseEntity<List<InterestRateLog>> getInterestByCodeInterestRate(@PathVariable String codeInterestRate) {
        return ResponseEntity.ok(this.service.getInterestByCodeInterestRate(codeInterestRate));
    }

    @GetMapping
    public ResponseEntity<List<InterestRateLog>> getAll() {
        return ResponseEntity.ok(this.service.getAllInterestRateLogs());
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<InterestRateLog>> getLogsByDateRange(@RequestParam Date startDate,
            @PathVariable Date endDate) {
        return ResponseEntity.ok(this.service.getInterestRateLogsByDateRange(startDate, endDate));

    }

    @PostMapping
    public ResponseEntity<InterestRateLogDTO> create(@RequestBody InterestRateLogDTO dto) {
        this.service.createInterestRateLog(dto);
        return ResponseEntity.ok(dto);
    }

}
