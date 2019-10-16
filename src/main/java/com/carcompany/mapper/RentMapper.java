package com.carcompany.mapper;

import com.carcompany.domain.Rent;
import com.carcompany.domain.dto.RentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RentMapper {
    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private CarMapper carMapper;

    @Autowired
    private RentalPlaceMapper rentalPlaceMapper;

    public RentDto mapRentToRentDto(final Rent rent) {
        return new RentDto(
                rent.getId(),
                customerMapper.mapCustomerToCustomerDto(rent.getCustomer()),
                carMapper.mapCarToCarDto(rent.getCar()),
                rentalPlaceMapper.mapRentalPlaceToRentalPlaceDto(rent.getRentalPlace()),
                rent.getRentStart(),
                rent.getRentStop()
        );
    }

    public Rent mapRentDtoToRent(final RentDto rentDto) {
        return new Rent(
                rentDto.getId(),
                customerMapper.mapCustomerDtoToCustomer(rentDto.getCustomerDto()),
                carMapper.mapCarDtoToCar(rentDto.getCarDto()),
                rentalPlaceMapper.mapRentalPlaceDtoToRentalPlace(rentDto.getRentalPlaceDto()),
                rentDto.getRentStart(),
                rentDto.getRentStop()
        );
    }

    public List<RentDto> mapRentListToRentDtoList(final List<Rent> rentList) {
        return rentList.stream()
                .map(rent -> new RentDto(
                        rent.getId(),
                        customerMapper.mapCustomerToCustomerDto(rent.getCustomer()),
                        carMapper.mapCarToCarDto(rent.getCar()),
                        rentalPlaceMapper.mapRentalPlaceToRentalPlaceDto(rent.getRentalPlace()),
                        rent.getRentStart(),
                        rent.getRentStop()))
                .collect(Collectors.toList());
    }
}
