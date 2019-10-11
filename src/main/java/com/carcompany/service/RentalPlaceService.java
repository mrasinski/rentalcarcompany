package com.carcompany.service;

import com.carcompany.domain.RentalPlace;
import com.carcompany.repository.RentalPlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RentalPlaceService {
    @Autowired
    private RentalPlaceRepository rentalPlaceRepository;

    public List<RentalPlace> getAllRentalPlaces() {
        return rentalPlaceRepository.findAll();
    }

    public Optional<RentalPlace> getRentalPlace(Long rentalPlaceId) {
        return rentalPlaceRepository.findById(rentalPlaceId);
    }

    public RentalPlace save(final RentalPlace rentalPlace) {
        return rentalPlaceRepository.save(rentalPlace);
    }

    public void deleteRentalPlace(Long rentalPlaceId) {
        rentalPlaceRepository.deleteById(rentalPlaceId);
    }
}
