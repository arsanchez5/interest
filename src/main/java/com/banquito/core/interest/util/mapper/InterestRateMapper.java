package com.banquito.core.interest.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import com.banquito.core.interest.controller.dto.InterestRateDTO;
import com.banquito.core.interest.model.InterestRate;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface InterestRateMapper {
    InterestRateDTO toDTO(InterestRate interestRate);
    InterestRate toPersistence(InterestRateDTO dto);
}
