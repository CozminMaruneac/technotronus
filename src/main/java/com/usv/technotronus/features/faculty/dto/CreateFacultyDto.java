package com.usv.technotronus.features.faculty.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateFacultyDto {

    private String acronym;
    private String name;
}
