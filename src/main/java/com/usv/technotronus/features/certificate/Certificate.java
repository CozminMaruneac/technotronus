package com.usv.technotronus.features.certificate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "certificate")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer number;

    private LocalDate releaseDate;

    private String facultyAcronym;



}
