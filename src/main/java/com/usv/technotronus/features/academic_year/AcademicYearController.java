package com.usv.technotronus.features.academic_year;

import com.usv.technotronus.features.academic_year.dto.AcademicYearDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/academic-year")
public class AcademicYearController {

    private final AcademicYearService service;

    @PostMapping
    public AcademicYearDto create(@RequestBody AcademicYearDto academicYearDto) {
        return service.create(academicYearDto);
    }

    @GetMapping("/current-year")
    public AcademicYearDto getCurrentAcademicYear() {
        return service.getCurrentAcademicYear();
    }

    @PatchMapping("/end-academic-year")
    public void endCurrentAcademicYear() {
        service.endAcademicYear();
    }
}
