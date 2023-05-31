package com.usv.technotronus.features.study_program.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StudyProgramDto {

    private Integer id;

    private String name;

    private String description;

    private String acronym;

    private UUID secretaryId;

    private String secretaryName;
}
