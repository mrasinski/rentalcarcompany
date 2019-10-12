package com.carcompany.controller;

import com.carcompany.domain.dto.CustomerDto;
import com.carcompany.exception.CustomerNotFoundException;
import com.carcompany.mapper.CustomerMapper;
import com.carcompany.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerMapper customerMapper;

    @GetMapping(value = "/customers")
    public List<CustomerDto> getCustomers() {
        return customerMapper.mapCustomerListToCustomerDtoList(customerService.getAllCustomers());
    }

    @GetMapping(value = "/customers/{customerId}")
    public CustomerDto getCustomer(@PathVariable("customerId") Long customerId) throws CustomerNotFoundException {
        return customerMapper.mapCustomerToCustomerDto(customerService.getCustomer(customerId).orElseThrow(CustomerNotFoundException::new));
    }

    @DeleteMapping(value = "/customers/{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Long customerId) {
        customerService.deleteCustomer(customerId);
    }

    @PostMapping(value = "/customers", consumes = APPLICATION_JSON_VALUE)
    public CustomerDto createCustomer(@RequestBody CustomerDto customerDto) {
        return customerMapper.mapCustomerToCustomerDto(customerService.saveCustomer(customerMapper.mapCustomerDtoToCustomer(customerDto)));
    }

    @PutMapping(value = "/customers")
    public CustomerDto updateCustomer(@RequestBody CustomerDto customerDto) {
        return customerMapper.mapCustomerToCustomerDto(customerService.saveCustomer(customerMapper.mapCustomerDtoToCustomer(customerDto)));
    }
}
