package com.banquito.core.interest.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
import com.banquito.core.interest.util.mapper.InterestRateLogMapper;

@RestController
@RequestMapping(path = "/interestLogs")
public class InterestRateLogController {
    private final InterestRateLogService service;
    private final InterestRateLogMapper interestRateLogMapper;

    public InterestRateLogController(InterestRateLogService service, InterestRateLogMapper interestRateLogMapper) {
        this.service = service;
        this.interestRateLogMapper = interestRateLogMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<InterestRateLogDTO> getById(@PathVariable Integer id) {
        try {
            System.out.println("Va a buscar Log por id:"+id);
            return ResponseEntity.ok(this.interestRateLogMapper.toDTO(this.service.getInterestLogById(id)));
        } catch (RuntimeException rte) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/state")
    public ResponseEntity<List<InterestRateLogDTO>> getInterestByState(@PathVariable String state) {
        try {
            System.out.println("Va a buscar Log por estado:"+state);
            List<InterestRateLog> interestRateLogs = this.service.getInterestByState(state);
            return ResponseEntity.ok(interestRateLogs.stream().map(bu -> this.interestRateLogMapper.toDTO(bu)).collect(Collectors.toList()));
        } catch (RuntimeException rte) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/codeInterestRate")
    public ResponseEntity<List<InterestRateLogDTO>> getInterestByCodeInterestRate(@PathVariable String codeInterestRate) {
        try {
            System.out.println("Va a buscar Log por codeInterestRate:"+codeInterestRate);
            List<InterestRateLog> interestRateLogs = this.service.getInterestByCodeInterestRate(codeInterestRate);
            return ResponseEntity.ok(interestRateLogs.stream().map(bu -> this.interestRateLogMapper.toDTO(bu)).collect(Collectors.toList()));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<InterestRateLogDTO>> getAll() {
        try {
            System.out.println("Va a buscar todos los Logs");
            List<InterestRateLog> interestRateLogs = this.service.getAllInterestRateLogs();
            return ResponseEntity.ok(interestRateLogs.stream().map(bu -> this.interestRateLogMapper.toDTO(bu)).collect(Collectors.toList()));
        } catch (RuntimeException rte) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<InterestRateLogDTO>> getLogsByDateRange(@RequestParam Date startDate, @PathVariable Date endDate) {
        try {
            System.out.println("Va a buscar Logs por rango de fechas");
            List<InterestRateLog> interestRateLogs = this.service.getInterestRateLogsByDateRange(startDate,endDate);
            return ResponseEntity.ok(interestRateLogs.stream().map(bu -> this.interestRateLogMapper.toDTO(bu)).collect(Collectors.toList()));
        } catch (RuntimeException rte) {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    public ResponseEntity<InterestRateLogDTO> create(@RequestBody InterestRateLogDTO dto) {
        try {
            InterestRateLogDTO interestRateLog = this.service.createInterestRateLog(dto);
            return ResponseEntity.ok(interestRateLog);
        } catch (Exception e) {
           return ResponseEntity.badRequest().build();
        }
    }

}
