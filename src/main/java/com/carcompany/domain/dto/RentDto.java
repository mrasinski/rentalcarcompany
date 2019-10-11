package com.carcompany.domain.dto;

import com.carcompany.domain.Car;
import com.carcompany.domain.Customer;
import com.carcompany.domain.RentalPlace;
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
    private Customer customer;
    private Car car;
    private RentalPlace rentalPlace;
    private LocalDateTime rentStart;
    private LocalDateTime rentStop;
    private BigDecimal priceOfRent;
}
