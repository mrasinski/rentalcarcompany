package com.carcompany.repository;

import com.carcompany.domain.Rent;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RentRepository extends CrudRepository<Rent, Long> {
    @Override
    List<Rent> findAll();

    @Override
    Optional<Rent> findById(Long rentId);

    @Override
    Rent save(Rent rent);

    @Override
    void deleteById(Long rentId);

    @Override
    long count();
}
