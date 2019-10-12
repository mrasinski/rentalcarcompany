package com.carcompany.service;

import com.carcompany.domain.Car;
import com.carcompany.repository.CarRepository;
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
public class CarServiceTest {
    @Autowired
    private CarService carService;

    @Autowired
    private CarRepository carRepository;

    @Test
    public void testSaveAndGetAllCars() {
        //Given
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();

        //When
        carService.saveCar(car1);
        carService.saveCar(car2);
        carService.saveCar(car3);
        List<Car> testCarList = carService.getAllCars();

        //Then
        assertEquals(3, testCarList.size());

        //CleanUp
        carService.deleteCar(car1.getId());
        carService.deleteCar(car2.getId());
        carService.deleteCar(car3.getId());
    }

    @Test
    public void testGetCar() {
        //Given
        Car car1 = new Car();
        carService.saveCar(car1);

        //When
        Optional<Car> testedCar = carService.getCar(car1.getId());

        //Then
        assertTrue(testedCar.isPresent());

        //CleanUp
        carService.deleteCar(car1.getId());
    }

    @Test
    public void testDeleteCar() {
        //Given
        Car car1 = new Car();
        carService.saveCar(car1);

        //When
        int beforeDelete = carService.getAllCars().size();
        carService.deleteCar(car1.getId());
        int afterDelete = carService.getAllCars().size();

        //Then
        assertEquals(1, beforeDelete - afterDelete);
    }
}