package com.usv.technotronus.features.domain;

import com.usv.technotronus.features.academic_year.AcademicYearRepository;
import com.usv.technotronus.features.academic_year.Status;
import com.usv.technotronus.features.domain.dto.CreateDomainDto;
import com.usv.technotronus.features.domain.dto.DomainDto;
import com.usv.technotronus.features.exceptions.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DomainService {

    private final ModelMapper modelMapper;
    private final DomainRepository repository;
    private final AcademicYearRepository academicYearRepository;

    public List<DomainDto> getAllDomains() {
        List<Domain> faculties = repository.findAll();
        return faculties.stream()
            .map(domain -> modelMapper.map(domain, DomainDto.class))
            .toList();
    }

    public DomainDto getDomainById(Integer id) {
        return repository.findById(id)
            .map(domain -> modelMapper.map(domain, DomainDto.class))
            .orElseThrow(BadRequestException::new);
    }

    public DomainDto createDomain(CreateDomainDto createDomainDto) {
        Domain domain = new Domain();
        domain.setName(createDomainDto.getName());
        domain.setAcademicYear(academicYearRepository.findFirstByStatus(Status.CURRENT).get().getCurrentAcademicYear());

        Domain savedDomain = repository.save(domain);

        return modelMapper.map(savedDomain, DomainDto.class);
    }

    public DomainDto updateDomain(Integer id, DomainDto updateDomainDto) {
        return repository.findById(id)
            .map(domain -> {
                domain.setName(updateDomainDto.getName());
                repository.save(domain);
                return modelMapper.map(domain, DomainDto.class);

            })
            .orElseThrow(BadRequestException::new);
    }

    public void deleteDomain(Integer id) {
        Domain domain = repository.findById(id)
            .orElseThrow(BadRequestException::new);
        repository.delete(domain);
    }

}
