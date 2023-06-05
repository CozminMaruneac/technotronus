package com.usv.technotronus.features.generated_certificate;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "generated_certificate")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GeneratedCertificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String registrationNumber;

    private LocalDate registrationDate;

    private String studentLastName;

    private String studentFirstName;

    private String fieldOfStudy;

    private String yearOfStudy;

    private String financialStatus;

    private String reason;
}
