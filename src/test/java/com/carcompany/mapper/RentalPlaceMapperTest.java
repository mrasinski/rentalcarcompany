package com.carcompany.mapper;

import com.carcompany.domain.RentalPlace;
import com.carcompany.domain.dto.RentalPlaceDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RentalPlaceMapperTest {
    @Autowired
    private RentalPlaceMapper rentalPlaceMapper;

    @Test
    public void testMapRentalPlaceDtoToRentalPlace() {
        //Given
        RentalPlaceDto rentalPlaceDto = new RentalPlaceDto(1L, "Warsaw", "Poland", "Domaniewska 39a");

        //When
        RentalPlace mappedPlace = rentalPlaceMapper.mapRentalPlaceDtoToRentalPlace(rentalPlaceDto);

        //Then
        assertEquals(new Long(1L), mappedPlace.getId());
        assertEquals("Warsaw", mappedPlace.getCity());
        assertEquals("Poland", mappedPlace.getCountry());
        assertEquals("Domaniewska 39a", mappedPlace.getStreet());
    }

    @Test
    public void testMapRentalPlaceToRentalPlaceDto() {
        //Given
        RentalPlace rentalPlace = new RentalPlace(1L, "Warsaw", "Poland", "Domaniewska 39a");

        //When
        RentalPlaceDto mappedPlace = rentalPlaceMapper.mapRentalPlaceToRentalPlaceDto(rentalPlace);

        //Then
        assertEquals(new Long(1L), mappedPlace.getId());
        assertEquals("Warsaw", mappedPlace.getCity());
        assertEquals("Poland", mappedPlace.getCountry());
        assertEquals("Domaniewska 39a", mappedPlace.getStreet());
    }

    @Test
    public void testMapRentalPlaceListToRentalPlaceDtoList() {
        //Given
        RentalPlace rentalPlace1 = new RentalPlace(1L, "Warsaw", "Poland", "Domaniewska 39a");
        RentalPlace rentalPlace2 = new RentalPlace(2L, "Cracow", "Poland", "3 Maja 3");
        List<RentalPlace> rentalPlaceList = new ArrayList<>();
        rentalPlaceList.add(rentalPlace1);
        rentalPlaceList.add(rentalPlace2);

        //When
        List<RentalPlaceDto> mappedList = rentalPlaceMapper.mapRentalPlaceListToRentalPlaceDtoList(rentalPlaceList);

        //Then
        assertEquals(2, mappedList.size());
    }
}