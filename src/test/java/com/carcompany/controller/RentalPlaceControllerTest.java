package com.carcompany.controller;

import com.carcompany.domain.dto.RentalPlaceDto;
import com.carcompany.mapper.RentalPlaceMapper;
import com.carcompany.service.RentalPlaceService;
import org.junit.Test;
import com.google.gson.Gson;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RentalPlaceController.class)
public class RentalPlaceControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RentalPlaceController rentalPlaceController;

    @MockBean
    private RentalPlaceService rentalPlaceService;

    @MockBean
    private RentalPlaceMapper rentalPlaceMapper;

    @Test
    public void testGetAllRentalPlaces() throws Exception {
        //Given
        List<RentalPlaceDto> rentalPlaceDtos = new ArrayList<>();
        RentalPlaceDto rentalPlaceDto = new RentalPlaceDto(
                1L,
                "Warsaw",
                "Poland",
                "Domaniewska 39a"
        );
        rentalPlaceDtos.add(rentalPlaceDto);
        when(rentalPlaceController.getRentalPlaces()).thenReturn(rentalPlaceDtos);

        //When & Then
        mockMvc.perform(get("/v1/rentalPlaces")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void testGetRentalPlace() throws Exception {
        //Given
        RentalPlaceDto rentalPlaceDto = new RentalPlaceDto(
                1L,
                "Warsaw",
                "Poland",
                "Domaniewska 39a"
        );
        when(rentalPlaceController.getRentalPlace(rentalPlaceDto.getId())).thenReturn(rentalPlaceDto);

        //When & Then
        mockMvc.perform(get("/v1/rentalPlaces/{rentalPlaceId}", rentalPlaceDto.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.city", is("Warsaw")))
                .andExpect(jsonPath("$.country", is("Poland")))
                .andExpect(jsonPath("$.street", is("Domaniewska 39a")));
    }

    @Test
    public void testDeleteRentalPlace() throws Exception {
        //Given
        RentalPlaceDto rentalPlaceDto = new RentalPlaceDto(
                1L,
                "Warsaw",
                "Poland",
                "Domaniewska 39a"
        );

        Gson gson = new Gson();
        String jsonContent = gson.toJson(rentalPlaceDto);

        //When & Then
        mockMvc.perform(delete("/v1/rentalPlaces/{rentalPlaceId}", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());

        verify(rentalPlaceController, times(1)).deleteRentalPlace(anyLong());
    }

    @Test
    public void testCreateRentalPlace() throws Exception {
        //Given
        RentalPlaceDto rentalPlaceDto = new RentalPlaceDto(
                1L,
                "Warsaw",
                "Poland",
                "Domaniewska 39a"
        );
        when(rentalPlaceController.createRentalPlace(any(RentalPlaceDto.class))).thenReturn(rentalPlaceDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(rentalPlaceDto);

        //When & Then
        mockMvc.perform(post("/v1/rentalPlaces")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.city", is("Warsaw")))
                .andExpect(jsonPath("$.country", is("Poland")))
                .andExpect(jsonPath("$.street", is("Domaniewska 39a")));
    }

    @Test
    public void testUpdateRentalPlace() throws Exception {
        //Given
        RentalPlaceDto updatedRentalPlaceDto = new RentalPlaceDto(
                1L,
                "Cracow",
                "Poland",
                "Aleja Piły 2"
        );
        when(rentalPlaceController.updateRentalPlace(any(RentalPlaceDto.class))).thenReturn(updatedRentalPlaceDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(updatedRentalPlaceDto);

        //When & Then
        mockMvc.perform(put("/v1/rentalPlaces")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.city", is("Cracow")))
                .andExpect(jsonPath("$.country", is("Poland")))
                .andExpect(jsonPath("$.street", is("Aleja Piły 2")));
    }
}