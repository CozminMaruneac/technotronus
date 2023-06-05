package com.usv.technotronus.features.generated_certificate;

import com.usv.technotronus.features.excel_generator.ExcelGenerator;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class GeneratedCertificateService {

    private final ExcelGenerator excelGenerator;
    private final GeneratedCertificateRepository repository;
    private final ModelMapper mapper;

    public String generateExcelReport(LocalDate dateFrom, LocalDate dateTo) {

        List<GeneratedCertificateDto> allByRegistrationDateBetween = repository.findAllByRegistrationDateBetween(dateFrom, dateTo)
            .stream().map(gc -> mapper.map(gc, GeneratedCertificateDto.class))
            .toList();

        return excelGenerator.generateExcelFile(allByRegistrationDateBetween);
    }

    public void create(GeneratedCertificateDto dto) {

        GeneratedCertificate generatedCertificate = mapper.map(dto, GeneratedCertificate.class);
        repository.save(generatedCertificate);
    }
}
