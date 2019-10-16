package com.carcompany.mapper;

import com.carcompany.domain.Customer;
import com.carcompany.domain.dto.CustomerDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomerMapperTest {
    @Autowired
    private CustomerMapper customerMapper;

    @Test
    public void testMapCustomerDtoToCustomer() {
        //Given
        CustomerDto customerDto = new CustomerDto(
                1L,
                "Aleks",
                "John",
                LocalDate.of(1980, 10, 19),
                "CAE12341");

        //When
        Customer mappedCustomer = customerMapper.mapCustomerDtoToCustomer(customerDto);

        //Then
        assertEquals(new Long(1L), mappedCustomer.getId());
        assertEquals("Aleks", mappedCustomer.getFirstname());
        assertEquals("John", mappedCustomer.getLastname());
        assertEquals(LocalDate.of(1980, 10, 19), mappedCustomer.getDateOfBirth());
        assertEquals("CAE12341", mappedCustomer.getDrivingLicenseId());
    }

    @Test
    public void testMapCustomerToCustomerDto() {
        //Given
        Customer customer = new Customer(
                1L,
                "Aleks",
                "John",
                LocalDate.of(1980, 10, 19),
                "CAE12341");

        //When
        CustomerDto mappedCustomerDto = customerMapper.mapCustomerToCustomerDto(customer);

        //Then
        assertEquals(new Long(1L), mappedCustomerDto.getId());
        assertEquals("Aleks", mappedCustomerDto.getFirstname());
        assertEquals("John", mappedCustomerDto.getLastname());
        assertEquals(LocalDate.of(1980, 10, 19), mappedCustomerDto.getDateOfBirth());
        assertEquals("CAE12341", mappedCustomerDto.getDrivingLicenseId());
    }

    @Test
    public void testMapCustomerListToCustomerDtoList() {
        //Given
        Customer customer1 = new Customer(
                1L,
                "Aleks",
                "John",
                LocalDate.of(1980, 10, 19),
                "CAE12341");
        Customer customer2 = new Customer(
                2L,
                "John",
                "Brown",
                LocalDate.of(1989, 5, 1),
                "BEE12152");
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer1);
        customerList.add(customer2);

        //When
        List<CustomerDto> mappedList = customerMapper.mapCustomerListToCustomerDtoList(customerList);

        //Then
        assertEquals(2, mappedList.size());
    }
}