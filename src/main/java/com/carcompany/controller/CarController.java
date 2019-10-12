package com.carcompany.controller;

import com.carcompany.domain.dto.CarDto;
import com.carcompany.exception.CarNotFoundException;
import com.carcompany.mapper.CarMapper;
import com.carcompany.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1")
public class CarController {
    @Autowired
    private CarService carService;

    @Autowired
    private CarMapper carMapper;

    @GetMapping(value = "/cars")
    public List<CarDto> getCars() {
        return carMapper.mapCarListToCarDtoList(carService.getAllCars());
    }

    @GetMapping(value = "/cars/{carId}")
    public CarDto getCar(@PathVariable("carId") Long carId) throws CarNotFoundException {
        return carMapper.mapCarToCarDto(carService.getCar(carId).orElseThrow(CarNotFoundException::new));
    }

    @DeleteMapping(value = "/cars/{carId}")
    public void deleteCar(@PathVariable("carId") Long carId) {
        carService.deleteCar(carId);
    }

    @PostMapping(value = "/cars", consumes = APPLICATION_JSON_VALUE)
    public CarDto createCar(@RequestBody CarDto carDto) {
        return carMapper.mapCarToCarDto(carService.saveCar(carMapper.mapCarDtoToCar(carDto)));
    }

    @PutMapping(value = "/cars")
    public CarDto updateCar(@RequestBody CarDto carDto) {
        return carMapper.mapCarToCarDto(carService.saveCar(carMapper.mapCarDtoToCar(carDto)));
    }
}
