package com.banquito.core.interest.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import com.banquito.core.interest.controller.dto.InterestRateLogDTO;
import com.banquito.core.interest.model.InterestRateLog;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface InterestRateLogMapper {
    InterestRateLogDTO toDTO(InterestRateLog interestRateLog);
    InterestRateLog toPersistence(InterestRateLogDTO dto);
}
