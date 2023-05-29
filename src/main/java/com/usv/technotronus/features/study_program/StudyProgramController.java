package com.usv.technotronus.features.study_program;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/study-programs")
@RestController
@RequiredArgsConstructor
public class StudyProgramController {

    private final StudyProgramService studyProgramService;
//
//    @PostMapping
//    public StudyProgramDto create(@RequestBody StudyProgramDto studyProgramDto){
//
//        return studyProgramService.create(studyProgramDto);
//    }
//
//    @GetMapping("/id/{id}")
//    public StudyProgramDto getById(@PathVariable Integer id){
//
//        return studyProgramService.getById(id);
//    }
//
//    @PutMapping
//    public StudyProgramDto update(@RequestBody StudyProgramDto dto) {
//
//        return studyProgramService.update(dto);
//    }
}
