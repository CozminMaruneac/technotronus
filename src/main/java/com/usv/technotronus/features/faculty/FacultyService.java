package com.usv.technotronus.features.faculty;

import com.usv.technotronus.features.exceptions.BadRequestException;
import com.usv.technotronus.features.faculty.dto.CreateFacultyDto;
import com.usv.technotronus.features.faculty.dto.FacultyDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FacultyService {

    private final ModelMapper modelMapper;
    private final FacultyRepository repository;


    public List<FacultyDto> getAllFaculties() {
        List<Faculty> faculties = repository.findAll();
        return faculties.stream()
            .map(faculty -> modelMapper.map(faculty, FacultyDto.class))
            .toList();
    }

    public FacultyDto getFacultyById(Integer id) {
        return repository.findById(id)
            .map(faculty -> modelMapper.map(faculty, FacultyDto.class))
            .orElseThrow(BadRequestException::new);
    }

    public FacultyDto createFaculty(CreateFacultyDto createFacultyDto) {
        Faculty faculty = new Faculty();
        faculty.setAcronym(createFacultyDto.getAcronym());
        faculty.setName(createFacultyDto.getName());

        Faculty savedFaculty = repository.save(faculty);

        return modelMapper.map(savedFaculty,FacultyDto.class);
    }

    public FacultyDto updateFaculty(Integer id, FacultyDto updateFacultyDto) {
        return repository.findById(id)
            .map(faculty -> {
                faculty.setAcronym(updateFacultyDto.getAcronym());
                faculty.setName(updateFacultyDto.getName());
                repository.save(faculty);
                return modelMapper.map(faculty, FacultyDto.class);

            })
            .orElseThrow(BadRequestException::new);
    }

    public void deleteFaculty(Integer id) {
        Faculty faculty = repository.findById(id)
            .orElseThrow(BadRequestException::new);
        repository.delete(faculty);
    }

}
