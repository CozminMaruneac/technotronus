package com.usv.technotronus.features.academic_year;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "academic_year")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AcademicYear {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String currentAcademicYear;

    @Enumerated(EnumType.STRING)
    private Status status;
}
