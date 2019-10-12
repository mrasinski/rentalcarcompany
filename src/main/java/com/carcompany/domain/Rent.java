package com.carcompany.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "rents")
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "carId")
    private Car car;

    @OneToOne
    @JoinColumn(name = "rentalPlaceId")
    private RentalPlace rentalPlace;

    @Column(name = "rentStart")
    private LocalDateTime rentStart;

    @Column(name = "rentStop")
    private LocalDateTime rentStop;

    @Column(name = "price")
    private BigDecimal priceOfRent;
}
