package com.carcompany.repository;

import com.carcompany.domain.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    @Override
    List<Customer> findAll();

    @Override
    Optional<Customer> findById(Long customerId);

    @Override
    Customer save(Customer customer);

    @Override
    void deleteById(Long customerId);

    @Override
    long count();
}
