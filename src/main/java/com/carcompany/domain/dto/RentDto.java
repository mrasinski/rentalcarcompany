package com.carcompany.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RentDto {
    private Long id;
    private CustomerDto customerDto;
    private CarDto carDto;
    private RentalPlaceDto rentalPlaceDto;
    private LocalDateTime rentStart;
    private LocalDateTime rentStop;
    private BigDecimal priceOfRent;
}
