package com.banquito.core.interest.controller.dto;


import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class InterestRateDTO {
    private String code;
    private String name;
    private String type;
    private Integer daysInMonth;
    private Integer daysInYear;
}
