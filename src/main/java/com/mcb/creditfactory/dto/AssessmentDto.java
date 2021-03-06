package com.mcb.creditfactory.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("assessment")
public class AssessmentDto implements Collateral{
    Long id;
    Long collateralId;
    String collateralType;
    LocalDate assessedDate;
    BigDecimal value;
}
