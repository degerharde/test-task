package com.mcb.creditfactory.model;

import com.mcb.creditfactory.dto.Collateral;
import com.mcb.creditfactory.external.CollateralType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ASSESSMENT")
public class Assessment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "COLLATERAL_ID")
    Long collateralId;

    @Column(name = "COLLATERAL_TYPE")
    String collateralType;

    @Column(name = "value")
    BigDecimal value;

    @Column(name = "assessed_Date")
    LocalDate localDate = LocalDate.now();

    public Assessment(Long collateralId, String collateralType, BigDecimal value) {
        this.collateralId = collateralId;
        this.collateralType = collateralType;
        this.value = value;
    }
}
