package com.usv.technotronus.features.study_program;

import com.usv.technotronus.features.domain.Domain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "study_program")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StudyProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String description;

    private String acronym;
}
