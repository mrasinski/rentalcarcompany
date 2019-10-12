package com.carcompany.service;

import com.carcompany.domain.RentalPlace;
import com.carcompany.repository.RentalPlaceRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class RentalPlaceServiceTest {
    @Autowired
    private RentalPlaceService rentalPlaceService;

    @Autowired
    private RentalPlaceRepository rentalPlaceRepository;

    @Test
    public void testSaveAndGetAllPlaces() {
        //Given
        RentalPlace rentalPlace1 = new RentalPlace();
        RentalPlace rentalPlace2 = new RentalPlace();
        RentalPlace rentalPlace3 = new RentalPlace();

        //When
        rentalPlaceService.saveRentalPlace(rentalPlace1);
        rentalPlaceService.saveRentalPlace(rentalPlace2);
        rentalPlaceService.saveRentalPlace(rentalPlace3);
        List<RentalPlace> testCarList = rentalPlaceService.getAllRentalPlaces();

        //Then
        assertEquals(3, testCarList.size());

        //CleanUp
        rentalPlaceService.deleteRentalPlace(rentalPlace1.getId());
        rentalPlaceService.deleteRentalPlace(rentalPlace2.getId());
        rentalPlaceService.deleteRentalPlace(rentalPlace3.getId());
    }

    @Test
    public void testGetCar() {
        //Given
        RentalPlace rentalPlace = new RentalPlace();
        rentalPlaceService.saveRentalPlace(rentalPlace);

        //When
        Optional<RentalPlace> testedRentalPlace = rentalPlaceService.getRentalPlace(rentalPlace.getId());

        //Then
        assertTrue(testedRentalPlace.isPresent());

        //CleanUp
        rentalPlaceService.deleteRentalPlace(rentalPlace.getId());
    }

    @Test
    public void testDeleteCar() {
        //Given
        RentalPlace rentalPlace = new RentalPlace();
        rentalPlaceService.saveRentalPlace(rentalPlace);

        //When
        int beforeDelete = rentalPlaceService.getAllRentalPlaces().size();
        rentalPlaceService.deleteRentalPlace(rentalPlace.getId());
        int afterDelete = rentalPlaceService.getAllRentalPlaces().size();

        //Then
        assertEquals(1, beforeDelete - afterDelete);
    }
}