package com.carcompany.controller;

import com.carcompany.domain.dto.CustomerDto;
import com.carcompany.mapper.CustomerMapper;
import com.carcompany.service.CustomerService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerController customerController;

    @MockBean
    private CustomerService customerService;

    @MockBean
    private CustomerMapper customerMapper;

    @Test
    public void testGetAllCustomers() throws Exception {
        //Given
        List<CustomerDto> customerDtos = new ArrayList<>();
        CustomerDto customerDto = new CustomerDto(
                1L,
                "John",
                "Lex",
                LocalDate.of(1980, 10, 12),
                "213ihasivh");
        customerDtos.add(customerDto);
        when(customerController.getCustomers()).thenReturn(customerDtos);

        //When & Then
        mockMvc.perform(get("/v1/customers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void testGetCustomer() throws Exception {
        //Given
        CustomerDto customerDto = new CustomerDto(
                1L,
                "John",
                "Lex",
                LocalDate.of(1980, 10, 12),
                "213ihasivh");
        when(customerController.getCustomer(customerDto.getId())).thenReturn(customerDto);

        //When & Then
        mockMvc.perform(get("/v1/customers/{customerId}", customerDto.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstname", is("John")))
                .andExpect(jsonPath("$.lastname", is("Lex")))
                .andExpect(jsonPath("$.dateOfBirth", is(customerDto.getDateOfBirth().toString())))
                .andExpect(jsonPath("$.drivingLicenseId", is("213ihasivh")));
    }

    @Test
    public void testDeleteCustomer() throws Exception {
        //Given
        CustomerDto customerDto = new CustomerDto(
                1L,
                "John",
                "Lex",
                LocalDate.of(1980, 10, 12),
                "213ihasivh");

        Gson gson = new Gson();
        String jsonContent = gson.toJson(customerDto);

        //When & Then
        mockMvc.perform(delete("/v1/customers/{customerId}", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());

        verify(customerController, times(1)).deleteCustomer(anyLong());
    }

    /*@Test
    public void testCreateCustomer() throws Exception {
        //Given
        CustomerDto customerDto = new CustomerDto(
                1L,
                "John",
                "Lex",
                LocalDate.of(1980, 10, 12),
                "213ihasivh");
        when(customerController.createCustomer(any(CustomerDto.class))).thenReturn(customerDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(customerDto);

        //When & Then
        mockMvc.perform(post("/v1/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstname", is("John")))
                .andExpect(jsonPath("$.lastname", is("Lex")))
                .andExpect(jsonPath("$.dateOfBirth", is("1980-10-12")))
                .andExpect(jsonPath("$.drivingLicenseId", is("213ihasivh")));
    }
    @Test
    public void testUpdateCustomer() throws Exception {
        //Given
        CustomerDto updatedCustomerDto = new CustomerDto(
                1L,
                "John",
                "Lex",
                LocalDate.of(1980, 10, 12),
                "213ihasivh");
        when(customerController.updateCustomer(any(CustomerDto.class))).thenReturn(updatedCustomerDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(updatedCustomerDto);

        //When & Then
        mockMvc.perform(put("/v1/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstname", is("John")))
                .andExpect(jsonPath("$.lastname", is("Lex")))
                .andExpect(jsonPath("$.dateOfBirth", is(updatedCustomerDto.getDateOfBirth().toString())))
                .andExpect(jsonPath("$.drivingLicenseId", is("213ihasivh")));
    }*/
}