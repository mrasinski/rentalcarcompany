package com.carcompany.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;
import static java.time.temporal.ChronoUnit.DAYS;

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
    private Long priceOfRent;

    public Rent(Long id, Customer customer, Car car, RentalPlace rentalPlace, LocalDateTime rentStart, LocalDateTime rentStop) {
        this.id = id;
        this.customer = customer;
        this.car = car;
        this.rentalPlace = rentalPlace;
        this.rentStart = rentStart;
        this.rentStop = rentStop;
        this.priceOfRent = DAYS.between(rentStart, rentStop) * car.getDailyPrice();
    }
}
