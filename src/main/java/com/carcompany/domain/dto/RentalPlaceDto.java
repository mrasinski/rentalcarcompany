package com.carcompany.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RentalPlaceDto {
    private Long id;
    private String city;
    private String country;
    private String street;
}
