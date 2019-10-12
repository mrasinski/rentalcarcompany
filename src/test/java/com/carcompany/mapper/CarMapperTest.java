package com.carcompany.mapper;

import com.carcompany.domain.Car;
import com.carcompany.domain.dto.CarDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class CarMapperTest {
    @Autowired
    private CarMapper carMapper;

    @Test
    public void testMapCarDtoToCar() {
        //Given
        CarDto carDto = new CarDto(1L, "BMW", "1", "Black", 2019, 380, 4, true);

        //When
        Car car = carMapper.mapCarDtoToCar(carDto);

        //Then
        assertEquals(new Long(1L), car.getId());
        assertEquals("BMW", car.getCompany());
        assertEquals("1", car.getModel());
        assertEquals("Black", car.getColor());
        assertEquals(2019, car.getYearOfProduction());
        assertEquals(380, car.getHorsePower());
        assertEquals(4, car.getEngine());
        assertTrue(car.isAutomatic());
    }

    @Test
    public void testMapCarToCarDto() {
        //Given
        Car car = new Car(1L, "BMW", "1", "Black", 2019, 380, 4, true);

        //When
        CarDto carDto = carMapper.mapCarToCarDto(car);

        //Then
        assertEquals(new Long(1L), carDto.getId());
        assertEquals("BMW", carDto.getCompany());
        assertEquals("1", carDto.getModel());
        assertEquals("Black", carDto.getColor());
        assertEquals(2019, carDto.getYearOfProduction());
        assertEquals(380, carDto.getHorsePower());
        assertEquals(4, carDto.getEngine());
        assertTrue(carDto.isAutomatic());
    }

    @Test
    public void testMapCarListToCarDtoList() {
        //Given
        Car car1 = new Car(1L, "BMW", "1", "Black", 2019, 380, 4, true);
        Car car2 = new Car(2L, "BMW", "1", "Black", 2019, 380, 4, true);
        List<Car> carList = new ArrayList<>();
        carList.add(car1);
        carList.add(car2);

        //When
        List<CarDto> mappedList = carMapper.mapCarListToCarDtoList(carList);

        //Then
        assertEquals(2, mappedList.size());
    }
}