package com.usv.technotronus.features.generated_certificate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GeneratedCertificateDto {

    private String registrationNumber;

    private LocalDate registrationDate;

    private String studentLastName;

    private String studentFirstName;

    private String fieldOfStudy;

    private String yearOfStudy;

    private String financialStatus;

    private String reason;
}
