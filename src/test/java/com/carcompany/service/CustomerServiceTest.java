package com.carcompany.service;

import com.carcompany.domain.Customer;
import com.carcompany.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class CustomerServiceTest {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testSaveAndGetAllCustomers() {
        //Given
        Customer customer1 = new Customer();
        Customer customer2 = new Customer();
        Customer customer3 = new Customer();

        //When
        customerService.saveCustomer(customer1);
        customerService.saveCustomer(customer2);
        customerService.saveCustomer(customer3);
        List<Customer> customerList = customerService.getAllCustomers();

        //Then
        assertEquals(3, customerList.size());

        //CleanUp
        customerService.deleteCustomer(customer1.getId());
        customerService.deleteCustomer(customer2.getId());
        customerService.deleteCustomer(customer3.getId());
    }

    @Test
    public void testGetCar() {
        //Given
        Customer customer = new Customer();
        customerService.saveCustomer(customer);

        //When
        Optional<Customer> testedCustomer = customerService.getCustomer(customer.getId());

        //Then
        assertTrue(testedCustomer.isPresent());

        //CleanUp
        customerService.deleteCustomer(customer.getId());
    }

    @Test
    public void testDeleteCar() {
        //Given
        Customer customer = new Customer();
        customerService.saveCustomer(customer);

        //When
        int beforeDelete = customerService.getAllCustomers().size();
        customerService.deleteCustomer(customer.getId());
        int afterDelete = customerService.getAllCustomers().size();

        //Then
        assertEquals(1, beforeDelete - afterDelete);
    }
}