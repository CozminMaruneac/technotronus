package com.usv.technotronus.features.study_program.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StudyProgramDto {

    private Integer id;

    private String name;

    private String description;

    private String acronym;

}
