package com.usv.technotronus.features.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class StudyProgramReportDto {
    private Integer totalStudents;
    private List<TotalStudentPerStudyProgramDto> studentPerStudyProgram;

    // Getters and setters
}
