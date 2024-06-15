package com.banquito.core.interest.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Builder;
import lombok.Value;
@Value
@Builder
public class InterestRateLogDTO {
    private Integer id;
    private String codeInterestRate;
    private BigDecimal value;
    private LocalDate startDate;
    private LocalDate endDate;
    private String state;
}
