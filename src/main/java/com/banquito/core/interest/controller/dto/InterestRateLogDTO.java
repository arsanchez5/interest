package com.banquito.core.interest.controller.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;
@Data
public class InterestRateLogDTO {
    private Integer id;
    private String codeInterestRate;
    private BigDecimal value;
    private Date startDate;
    private Date endDate;
    private String state;
}
