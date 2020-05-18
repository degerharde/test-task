package com.mcb.creditfactory.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("airplane")
public class AirplaneDto implements Collateral {
    private Long id;
    private String brand;
    private String model;
    private String manufacturer;
    private Short year;
    private Integer fuelCapacity;
    private Integer seats;
    private BigDecimal value;

    public AirplaneDto(String brand, String model, String manufacturer, Short year, Integer fuelCapacity, Integer seats, BigDecimal value) {
        this.brand = brand;
        this.model = model;
        this.manufacturer = manufacturer;
        this.year = year;
        this.fuelCapacity = fuelCapacity;
        this.seats = seats;
        this.value = value;
    }

    public AirplaneDto(Long id) {
        this.id = id;
    }
}
