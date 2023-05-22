package com.usv.technotronus.features.faculty;

import com.usv.technotronus.features.faculty.dto.CreateFacultyDto;
import com.usv.technotronus.features.faculty.dto.FacultyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/faculties")
@RequiredArgsConstructor
public class FacultyController {

    private final FacultyService facultyService;

    @GetMapping
    public List<FacultyDto> getAllFaculties() {
        return facultyService.getAllFaculties();
    }

    @GetMapping("/{id}")
    public FacultyDto getFacultyById(@PathVariable Integer id) {
        return facultyService.getFacultyById(id);
    }

    @PostMapping
    public ResponseEntity<FacultyDto> createFaculty(@RequestBody CreateFacultyDto createFacultyDto) {
        FacultyDto createdFaculty = facultyService.createFaculty(createFacultyDto);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(createdFaculty);
    }

    @PutMapping("/{id}")
    public FacultyDto updateFaculty(@PathVariable Integer id, @RequestBody FacultyDto updateFacultyDto) {
        return facultyService.updateFaculty(id, updateFacultyDto);
    }

    @DeleteMapping("/{id}")
    public void deleteFaculty(@PathVariable Integer id) {
        facultyService.deleteFaculty(id);
    }
}
