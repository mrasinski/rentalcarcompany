package com.carcompany.controller;

import com.carcompany.domain.dto.RentDto;
import com.carcompany.exception.RentException;
import com.carcompany.mapper.RentMapper;
import com.carcompany.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1")
public class RentController {
    @Autowired
    private RentService rentService;

    @Autowired
    private RentMapper rentMapper;

    @GetMapping(value = "/rents")
    public List<RentDto> getRents() {
        return rentMapper.mapRentListToRentDtoList(rentService.getAllRents());
    }

    @GetMapping(value = "/rents/{rentId}")
    public RentDto getRent(@PathVariable("rentId") Long rentId) throws RentException {
        return rentMapper.mapRentToRentDto(rentService.getRent(rentId).orElseThrow(RentException::new));
    }

    @DeleteMapping(value = "/rents/{rentId}")
    public void deleteRent(@PathVariable("rentId") Long rentId) {
        rentService.deleteRent(rentId);
    }

    @PostMapping(value = "/rents", consumes = APPLICATION_JSON_VALUE)
    public RentDto createRent(@RequestBody RentDto rentDto) {
        return rentMapper.mapRentToRentDto(rentService.saveRent(rentMapper.mapRentDtoToRent(rentDto)));
    }

    @PutMapping(value = "/rents")
    public RentDto updateRent(@RequestBody RentDto rentDto) {
        return rentMapper.mapRentToRentDto(rentService.saveRent(rentMapper.mapRentDtoToRent(rentDto)));
    }
}
