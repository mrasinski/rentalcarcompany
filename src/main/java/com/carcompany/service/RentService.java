package com.carcompany.service;

import com.carcompany.domain.Rent;
import com.carcompany.repository.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RentService {
    @Autowired
    private RentRepository rentRepository;

    public List<Rent> getAllRents() {
        return rentRepository.findAll();
    }

    public Optional<Rent> getRent(Long rentId) {
        return rentRepository.findById(rentId);
    }

    public Rent saveRent(final Rent rent) {
        return rentRepository.save(rent);
    }

    public void deleteRent(Long rentId) {
        rentRepository.deleteById(rentId);
    }

    public long count() {
        return rentRepository.count();
    }
}
