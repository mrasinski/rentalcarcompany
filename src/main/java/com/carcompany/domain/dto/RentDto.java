package com.carcompany.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@NoArgsConstructor
@Getter
public class RentDto {
    private Long id;
    private CustomerDto customerDto;
    private CarDto carDto;
    private RentalPlaceDto rentalPlaceDto;
    private LocalDateTime rentStart;
    private LocalDateTime rentStop;
    private Long priceOfRent;

    public RentDto(Long id, CustomerDto customerDto, CarDto carDto, RentalPlaceDto rentalPlaceDto, LocalDateTime rentStart, LocalDateTime rentStop) {
        this.id = id;
        this.customerDto = customerDto;
        this.carDto = carDto;
        this.rentalPlaceDto = rentalPlaceDto;
        this.rentStart = rentStart;
        this.rentStop = rentStop;
        this.priceOfRent = ChronoUnit.DAYS.between(rentStart, rentStop) * carDto.getDailyPrice();
    }
}
