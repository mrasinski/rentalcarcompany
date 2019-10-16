package com.carcompany.service;

import com.carcompany.domain.Rent;
import com.carcompany.repository.RentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RentServiceTest {
    @Autowired
    private RentService rentService;

    @Autowired
    private RentRepository rentRepository;

    @Test
    public void testSaveAndGetAllRents() {
        //Given
        Rent rent1 = new Rent();
        Rent rent2 = new Rent();
        Rent rent3 = new Rent();

        //When
        rentService.saveRent(rent1);
        rentService.saveRent(rent2);
        rentService.saveRent(rent3);
        List<Rent> rentList = rentService.getAllRents();

        //Then
        assertEquals(3, rentList.size());

        //CleanUp
        rentService.deleteRent(rent1.getId());
        rentService.deleteRent(rent2.getId());
        rentService.deleteRent(rent3.getId());
    }

    @Test
    public void testGetRent() {
        //Given
        Rent rent = new Rent();
        rentService.saveRent(rent);

        //When
        Optional<Rent> testedRent = rentService.getRent(rent.getId());

        //Then
        assertTrue(testedRent.isPresent());

        //CleanUp
        rentService.deleteRent(rent.getId());
    }

    @Test
    public void testDeleteRent() {
        //Given
        Rent rent = new Rent();
        rentService.saveRent(rent);

        //When
        int beforeDelete = rentService.getAllRents().size();
        rentService.deleteRent(rent.getId());
        int afterDelete = rentService.getAllRents().size();

        //Then
        assertEquals(1, beforeDelete - afterDelete);
    }

}