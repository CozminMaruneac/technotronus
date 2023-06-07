package com.usv.technotronus.features.academic_year;

import com.usv.technotronus.features.academic_year.dto.AcademicYearDto;
import com.usv.technotronus.features.exceptions.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AcademicYearService {

    private final ModelMapper modelMapper;
    private final AcademicYearRepository academicYearRepository;


    public AcademicYearDto create(AcademicYearDto academicYearDto) {

        if (Boolean.TRUE.equals(academicYearRepository.existsAcademicYearByStatus(Status.CURRENT))) {
            throw new BadRequestException("An existing academic year is going on now. You have to close the previous first!");
        }
        AcademicYear academicYear = modelMapper.map(academicYearDto, AcademicYear.class);
        academicYear.setStatus(Status.CURRENT);
        return modelMapper.map(academicYearRepository.save(academicYear), AcademicYearDto.class);
    }

    public AcademicYearDto getCurrentAcademicYear() {

        return academicYearRepository.findFirstByStatus(Status.CURRENT)
            .map(academicYear -> modelMapper.map(academicYear, AcademicYearDto.class))
            .orElseThrow(BadRequestException::new);
    }

    public void endAcademicYear() {

        Optional<AcademicYear> academicYear = academicYearRepository.findFirstByStatus(Status.CURRENT);
        academicYear.ifPresent(ay -> {
            ay.setStatus(Status.PREVIOUS);
            academicYearRepository.save(ay);
        });
    }
}
