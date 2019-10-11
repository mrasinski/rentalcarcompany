package com.carcompany.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CustomerDto {
    private Long id;
    private String firstname;
    private String lastname;
    private LocalDate dateOfBirth;
    private String drivingLicenseId;
}
