package com.usv.technotronus.features.study_program;

import com.usv.technotronus.features.study_program.dto.StudyProgramDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/study-programs")
@RestController
@RequiredArgsConstructor
public class StudyProgramController {

    private final StudyProgramService studyProgramService;

    @PostMapping
    public StudyProgramDto create(@RequestBody StudyProgramDto studyProgramDto) {

        return studyProgramService.create(studyProgramDto);
    }

    @GetMapping("/{id}")
    public StudyProgramDto getById(@PathVariable Integer id) {

        return studyProgramService.getById(id);
    }

    @GetMapping("")
    public List<StudyProgramDto> getAll() {

        return studyProgramService.getAll();
    }

    @PutMapping
    public StudyProgramDto update(@RequestBody StudyProgramDto dto) {

        return studyProgramService.update(dto);
    }


}
