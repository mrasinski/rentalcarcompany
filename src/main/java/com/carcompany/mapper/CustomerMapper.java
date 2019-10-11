package com.carcompany.mapper;

import com.carcompany.domain.Customer;
import com.carcompany.domain.dto.CustomerDto;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerMapper {
    public Customer mapCustomerDtoToCustomer(final CustomerDto customerDto) {
        return new Customer(
                customerDto.getId(),
                customerDto.getFirstname(),
                customerDto.getLastname(),
                customerDto.getDateOfBirth(),
                customerDto.getDrivingLicenseId()
        );
    }

    public CustomerDto mapCustomerToCustomerDto(final Customer customer) {
        return new CustomerDto(
                customer.getId(),
                customer.getFirstname(),
                customer.getLastname(),
                customer.getDateOfBirth(),
                customer.getDrivingLicenseId()
        );
    }

    public List<CustomerDto> mapCustomerListToCustomerDtoList(final List<Customer> customerList) {
        return customerList.stream()
                .map(customer -> new CustomerDto(customer.getId(), customer.getFirstname(), customer.getLastname(),
                        customer.getDateOfBirth(), customer.getDrivingLicenseId()))
                .collect(Collectors.toList());
    }
}
