package com.carcompany.service;

import com.carcompany.domain.Car;
import com.carcompany.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Optional<Car> getCar(Long carId) {
        return carRepository.findById(carId);
    }

    public Car saveCar(final Car car) {
        return carRepository.save(car);
    }

    public void deleteCar(Long carId) {
        carRepository.deleteById(carId);
    }

    public long count(){
        return carRepository.count();
    }
}
