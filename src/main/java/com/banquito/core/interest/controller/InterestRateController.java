package com.banquito.core.interest.controller;

import java.util.List;
import java.util.stream.Collectors;

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
import com.banquito.core.interest.util.mapper.InterestRateMapper;

@RestController
@RequestMapping(path = "/interests")
public class InterestRateController {
    private final InterestRateService service;
    private final InterestRateMapper interestRateMapper;

    public InterestRateController(InterestRateService service, InterestRateMapper interestRateMapper) {
        this.service = service;
        this.interestRateMapper = interestRateMapper;
    }

    @GetMapping
    public ResponseEntity<List<InterestRateDTO>> getAll() {
        try {
            List<InterestRate> interestRates = this.service.getAllInterestRates();
            return ResponseEntity.ok(interestRates.stream().map(bu -> this.interestRateMapper.toDTO(bu)).collect(Collectors.toList()));
        } catch (RuntimeException rte) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{code}")
    public ResponseEntity<InterestRateDTO> getInterestById(@PathVariable String code) {
        try {
            System.out.println("Va a buscar usuario por id:"+code);
            return ResponseEntity.ok(this.interestRateMapper.toDTO(this.service.getInterestById(code)));
        } catch (RuntimeException rte) {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/name")
    public ResponseEntity<InterestRateDTO> getInterestByName(@PathVariable String name) {
        try {
            System.out.println("Va a buscar usuario por nombre:"+name);
            return ResponseEntity.ok(this.interestRateMapper.toDTO(this.service.getInterestByName(name)));
        } catch (RuntimeException rte) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/type")
    public ResponseEntity<List<InterestRateDTO>> getInterestByType(@PathVariable String type) {
        try {
            List<InterestRate> interestRates = this.service.getInterestByType(type);
            return ResponseEntity.ok(interestRates.stream().map(bu -> this.interestRateMapper.toDTO((bu))).collect(Collectors.toList()));
        } catch (RuntimeException rte) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<InterestRateDTO> create(@RequestBody InterestRateDTO interestRateDTO) {
        try {
            InterestRateDTO dtoIR = this.service.createInterestRate(interestRateDTO);
            return ResponseEntity.ok(dtoIR);
        } catch (RuntimeException rte) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{code}")
    public ResponseEntity<InterestRateDTO> updateInterestRate(@PathVariable String code, @RequestBody InterestRateDTO interestRateDTO) {
        System.out.println("Va a cambiar la tasa de interés:" + interestRateDTO.toString());
        try {
            InterestRateDTO dtoIR = this.service.updateInterestRate(code, interestRateDTO);
            return ResponseEntity.ok(dtoIR);
        } catch (RuntimeException rte) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteInterestRate(@PathVariable String code) {
        try {
            this.service.deleteInterestRate(code);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
