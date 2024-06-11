package com.banquito.core.interest.controller.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class InterestRateDTO {
    private String code;
    private String name;
    private String type;
    private BigDecimal daysInMonth;
    private BigDecimal daysInYear;
}
