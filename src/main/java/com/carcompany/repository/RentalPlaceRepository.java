package com.carcompany.repository;

import com.carcompany.domain.RentalPlace;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RentalPlaceRepository extends CrudRepository<RentalPlace, Long> {
    @Override
    List<RentalPlace> findAll();

    @Override
    Optional<RentalPlace> findById(Long rentalPlaceId);

    @Override
    RentalPlace save(RentalPlace rentalPlace);

    @Override
    void deleteById(Long rentalPlaceId);
}
