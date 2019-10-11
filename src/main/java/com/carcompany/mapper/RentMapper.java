package com.carcompany.mapper;

import com.carcompany.domain.Rent;
import com.carcompany.domain.dto.RentDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RentMapper {
    public Rent mapRentDtoToRent(final RentDto rentDto) {
        return new Rent(
                rentDto.getId(),
                rentDto.getCustomer(),
                rentDto.getCar(),
                rentDto.getRentalPlace(),
                rentDto.getRentStart(),
                rentDto.getRentStop(),
                rentDto.getPriceOfRent()
        );
    }

    public RentDto mapRentToRentDto(final Rent rent) {
        return new RentDto(
                rent.getId(),
                rent.getCustomer(),
                rent.getCar(),
                rent.getRentalPlace(),
                rent.getRentStart(),
                rent.getRentStop(),
                rent.getPriceOfRent()
        );
    }

    public List<RentDto> mapRentListToRentDtoList(final List<Rent> rentList) {
        return rentList.stream()
                .map(rent -> new RentDto(rent.getId(), rent.getCustomer(), rent.getCar(), rent.getRentalPlace(),
                        rent.getRentStart(), rent.getRentStop(), rent.getPriceOfRent()))
                .collect(Collectors.toList());
    }
}
