package com.carcompany.mapper;

import com.carcompany.domain.Car;
import com.carcompany.domain.dto.CarDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarMapper {
    public CarDto mapCarToCarDto(final Car car) {
        return new CarDto(
                car.getId(),
                car.getCompany(),
                car.getModel(),
                car.getColor(),
                car.getYearOfProduction(),
                car.getHorsePower(),
                car.getEngine(),
                car.isAutomatic()
        );
    }

    public Car mapCarDtoToCar(final CarDto carDto) {
        return new Car(
                carDto.getId(),
                carDto.getCompany(),
                carDto.getModel(),
                carDto.getColor(),
                carDto.getYearOfProduction(),
                carDto.getHorsePower(),
                carDto.getEngine(),
                carDto.isAutomatic()
        );
    }

    public List<CarDto> mapCarListToCarDtoList(final List<Car> carList) {
        return carList.stream()
                .map(car -> new CarDto(car.getId(), car.getCompany(), car.getModel(), car.getColor(), car.getYearOfProduction(),
                        car.getHorsePower(), car.getEngine(), car.isAutomatic()))
                .collect(Collectors.toList());
    }
}
