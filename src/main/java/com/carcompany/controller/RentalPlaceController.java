package com.carcompany.controller;

import com.carcompany.domain.dto.RentalPlaceDto;
import com.carcompany.exception.RentalPlaceException;
import com.carcompany.mapper.RentalPlaceMapper;
import com.carcompany.service.RentalPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1")
public class RentalPlaceController {
    @Autowired
    private RentalPlaceService rentalPlaceService;

    @Autowired
    private RentalPlaceMapper rentalPlaceMapper;

    @GetMapping(value = "/rentalPlaces")
    public List<RentalPlaceDto> getRentalPlaces() {
        return rentalPlaceMapper.mapRentalPlaceListToRentalPlaceDtoList(rentalPlaceService.getAllRentalPlaces());
    }

    @GetMapping(value = "/rentalPlaces/{rentalPlaceId}")
    public RentalPlaceDto getRentalPlace(@PathVariable("rentalPlaceId") Long rentalPlaceId) throws RentalPlaceException {
        return rentalPlaceMapper.mapRentalPlaceToRentalPlaceDto(rentalPlaceService.getRentalPlace(rentalPlaceId).orElseThrow(RentalPlaceException::new));
    }

    @DeleteMapping(value = "/rentalPlaces/{rentalPlaceId}")
    public void deleteRentalPlace(@PathVariable("rentalPlaceId") Long rentalPlaceId) {
        rentalPlaceService.deleteRentalPlace(rentalPlaceId);
    }

    @PostMapping(value = "/rentalPlaces", consumes = APPLICATION_JSON_VALUE)
    public RentalPlaceDto createRentalPlace(@RequestBody RentalPlaceDto rentalPlaceDto) {
        return rentalPlaceMapper.mapRentalPlaceToRentalPlaceDto(rentalPlaceService.saveRentalPlace(rentalPlaceMapper.mapRentalPlaceDtoToRentalPlace(rentalPlaceDto)));
    }

    @PutMapping(value = "/rentalPlaces")
    public RentalPlaceDto updateRentalPlace(@RequestBody RentalPlaceDto rentalPlaceDto) {
        return rentalPlaceMapper.mapRentalPlaceToRentalPlaceDto(rentalPlaceService.saveRentalPlace(rentalPlaceMapper.mapRentalPlaceDtoToRentalPlace(rentalPlaceDto)));
    }
}
