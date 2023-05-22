package com.usv.technotronus.features.academic_year.dto;

import com.usv.technotronus.features.academic_year.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class AcademicYearDto {

    private Integer id;

    private String currentAcademicYear;

    @Enumerated(EnumType.STRING)
    @JsonIgnore
    private Status status;
}
