package com.carcompany.controller;

import com.carcompany.domain.dto.CarDto;
import com.carcompany.mapper.CarMapper;
import com.carcompany.service.CarService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
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
@WebMvcTest(CarController.class)
public class CarControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarController carController;

    @MockBean
    private CarService carService;

    @MockBean
    private CarMapper carMapper;

    @Test
    public void testGetAllCars() throws Exception {
        //Given
        List<CarDto> carDtos = new ArrayList<>();
        CarDto carDto = new CarDto(
                1L,
                "BMW",
                "1",
                "Black",
                2019,
                320,
                4,
                true,
                120L);
        carDtos.add(carDto);
        when(carController.getCars()).thenReturn(carDtos);

        //When & Then
        mockMvc.perform(get("/v1/cars").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void testGetCar() throws Exception {
        //Given
        CarDto carDto = new CarDto(
                1L,
                "BMW",
                "1",
                "Black",
                2019,
                320,
                4,
                true,
                120L);
        when(carController.getCar(carDto.getId())).thenReturn(carDto);

        //When & Then
        mockMvc.perform(get("/v1/cars/{carId}", carDto.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.company", is("BMW")))
                .andExpect(jsonPath("$.model", is("1")))
                .andExpect(jsonPath("$.yearOfProduction", is(2019)))
                .andExpect(jsonPath("$.horsePower", is(320)))
                .andExpect(jsonPath("$.engine", is(4)))
                .andExpect(jsonPath("$.dailyPrice", is(120)))
                .andExpect(jsonPath("$.automatic", is(true)));
    }

    @Test
    public void testDeleteCar() throws Exception {
        //Given
        CarDto carDto = new CarDto(
                1L,
                "BMW",
                "1",
                "Black",
                2019,
                320,
                4,
                true,
                120L);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(carDto);

        //When & Then
        mockMvc.perform(delete("/v1/cars/{carId}", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());

        verify(carController, times(1)).deleteCar(anyLong());
    }

    @Test
    public void testCreateCar() throws Exception {
        //Given
        CarDto carDto = new CarDto(
                1L,
                "BMW",
                "1",
                "Black",
                2019,
                320,
                4,
                true,
                120L);
        when(carController.createCar(any(CarDto.class))).thenReturn(carDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(carDto);

        //When & Then
        mockMvc.perform(post("/v1/cars")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.company", is("BMW")))
                .andExpect(jsonPath("$.model", is("1")))
                .andExpect(jsonPath("$.yearOfProduction", is(2019)))
                .andExpect(jsonPath("$.horsePower", is(320)))
                .andExpect(jsonPath("$.engine", is(4)))
                .andExpect(jsonPath("$.dailyPrice", is(120)))
                .andExpect(jsonPath("$.automatic", is(true)));
    }

    @Test
    public void testUpdateCar() throws Exception {
        //Given
        CarDto updatedCarDto = new CarDto(
                1L,
                "BMW",
                "1",
                "Black",
                2019,
                320,
                4,
                true,
                120L);
        when(carController.updateCar(any(CarDto.class))).thenReturn(updatedCarDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(updatedCarDto);

        //When & Then
        mockMvc.perform(put("/v1/cars")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.company", is("BMW")))
                .andExpect(jsonPath("$.model", is("1")))
                .andExpect(jsonPath("$.yearOfProduction", is(2019)))
                .andExpect(jsonPath("$.horsePower", is(320)))
                .andExpect(jsonPath("$.engine", is(4)))
                .andExpect(jsonPath("$.dailyPrice", is(120)))
                .andExpect(jsonPath("$.automatic", is(true)));
    }
}