package com.carcompany.mapper;

import com.carcompany.domain.RentalPlace;
import com.carcompany.domain.dto.RentalPlaceDto;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RentalPlaceMapper {
    public RentalPlaceDto mapRentalPlaceToRentalPlaceDto(final RentalPlace rentalPlace) {
        return new RentalPlaceDto(
                rentalPlace.getId(),
                rentalPlace.getCity(),
                rentalPlace.getCountry(),
                rentalPlace.getStreet()
        );
    }

    public RentalPlace mapRentalPlaceDtoToRentalPlace(final RentalPlaceDto rentalPlaceDto) {
        return new RentalPlace(
                rentalPlaceDto.getId(),
                rentalPlaceDto.getCity(),
                rentalPlaceDto.getCountry(),
                rentalPlaceDto.getStreet()
        );
    }

    public List<RentalPlaceDto> mapRentalPlaceListToRentalPlaceDtoList(final List<RentalPlace> rentalPlaceList) {
        return rentalPlaceList.stream()
                .map(rentalPlace -> new RentalPlaceDto(rentalPlace.getId(), rentalPlace.getCity(), rentalPlace.getCountry(),
                        rentalPlace.getStreet()))
                .collect(Collectors.toList());
    }
}
