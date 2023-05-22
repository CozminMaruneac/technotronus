package com.usv.technotronus.features.faculty.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FacultyDto {
    private Integer id;
    private String acronym;
    private String name;

}