package com.carcompany.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CarDto {
    private Long id;
    private String company;
    private String model;
    private String color;
    private int yearOfProduction;
    private int horsePower;
    private int engine;
    private boolean isAutomatic;
}
