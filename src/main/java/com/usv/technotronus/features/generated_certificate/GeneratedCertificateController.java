package com.usv.technotronus.features.generated_certificate;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/generated-certificates")
@RequiredArgsConstructor
public class GeneratedCertificateController {

    private final GeneratedCertificateService generatedCertificateService;

    @GetMapping
    public String generateExcelReport(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
                                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo) {

        return generatedCertificateService.generateExcelReport(dateFrom, dateTo);
    }
}
