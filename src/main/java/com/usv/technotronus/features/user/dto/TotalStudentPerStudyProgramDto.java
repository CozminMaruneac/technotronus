package com.usv.technotronus.features.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TotalStudentPerStudyProgramDto {
    private String studyProgramName;
    private Integer studentsNumber;

}
