package com.usv.technotronus.features.study_program;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyProgramRepository extends JpaRepository<StudyProgram, Integer> {

    StudyProgram findByName(String name);
}
