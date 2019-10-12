package com.carcompany.mapper;

import com.carcompany.domain.Car;
import com.carcompany.domain.Customer;
import com.carcompany.domain.Rent;
import com.carcompany.domain.RentalPlace;
import com.carcompany.domain.dto.CarDto;
import com.carcompany.domain.dto.CustomerDto;
import com.carcompany.domain.dto.RentDto;
import com.carcompany.domain.dto.RentalPlaceDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class RentMapperTest {
    @Autowired
    private RentMapper rentMapper;

    @Test
    void mapRentToRentDto() {
        //Given
        Customer customer = new Customer(1L, "Aleks", "John", LocalDate.of(1980, 10, 19), "CAE12341");
        Car car = new Car(1L, "BMW", "1", "Black", 2019, 380, 4, true);
        RentalPlace rentalPlace = new RentalPlace(1L, "Warsaw", "Poland", "Domaniewska 39a");
        Rent rent = new Rent(1L, customer, car, rentalPlace, LocalDateTime.of(2019, 10, 12, 14, 25),
                LocalDateTime.of(2019, 10, 14, 15, 0), new BigDecimal(123.23));

        //When
        RentDto mappedRent = rentMapper.mapRentToRentDto(rent);

        //Then
        assertEquals(new Long(1L), mappedRent.getId());
        assertEquals("1", mappedRent.getCarDto().getModel());
        assertEquals("John", mappedRent.getCustomerDto().getLastname());
        assertEquals("Warsaw", mappedRent.getRentalPlaceDto().getCity());
        assertEquals(LocalDateTime.of(2019, 10, 12, 14, 25), mappedRent.getRentStart());
        assertEquals(LocalDateTime.of(2019, 10, 14, 15, 0), mappedRent.getRentStop());
        assertEquals(new BigDecimal(123.23), mappedRent.getPriceOfRent());
    }

    @Test
    void mapRentDtoToRent() {
        //Given
        CustomerDto customerDto = new CustomerDto(1L, "Aleks", "John", LocalDate.of(1980, 10, 19), "CAE12341");
        CarDto carDto = new CarDto(1L, "BMW", "1", "Black", 2019, 380, 4, true);
        RentalPlaceDto rentalPlaceDto = new RentalPlaceDto(1L, "Warsaw", "Poland", "Domaniewska 39a");
        RentDto rentDto = new RentDto(1L, customerDto, carDto, rentalPlaceDto, LocalDateTime.of(2019, 10, 12, 14, 25),
                LocalDateTime.of(2019, 10, 14, 15, 0), new BigDecimal(123.23));

        //When
        Rent mappedRent = rentMapper.mapRentDtoToRent(rentDto);

        //Then
        assertEquals(new Long(1L), mappedRent.getId());
        assertEquals("1", mappedRent.getCar().getModel());
        assertEquals("John", mappedRent.getCustomer().getLastname());
        assertEquals("Warsaw", mappedRent.getRentalPlace().getCity());
        assertEquals(LocalDateTime.of(2019, 10, 12, 14, 25), mappedRent.getRentStart());
        assertEquals(LocalDateTime.of(2019, 10, 14, 15, 0), mappedRent.getRentStop());
        assertEquals(new BigDecimal(123.23), mappedRent.getPriceOfRent());
    }

    @Test
    void mapRentListToRentDtoList() {
        //Given
        Customer customer = new Customer(1L, "Aleks", "John", LocalDate.of(1980, 10, 19), "CAE12341");
        Car car = new Car(1L, "BMW", "1", "Black", 2019, 380, 4, true);
        RentalPlace rentalPlace = new RentalPlace(1L, "Warsaw", "Poland", "Domaniewska 39a");
        Rent rent1 = new Rent(1L, customer, car, rentalPlace, LocalDateTime.of(2019, 10, 12, 14, 25),
                LocalDateTime.of(2019, 10, 14, 15, 0), new BigDecimal(123.23));
        Rent rent2 = new Rent(2L, customer, car, rentalPlace, LocalDateTime.of(2019, 7, 13, 14, 25),
                LocalDateTime.of(2019, 7, 14, 19, 0), new BigDecimal(1593.23));
        List<Rent> rentList = new ArrayList<>();
        rentList.add(rent1);
        rentList.add(rent2);

        //When
        List<RentDto> mappedList = rentMapper.mapRentListToRentDtoList(rentList);

        //Then
        assertEquals(2, mappedList.size());
    }
}