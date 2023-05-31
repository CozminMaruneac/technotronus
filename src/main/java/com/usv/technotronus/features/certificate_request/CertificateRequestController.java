package com.usv.technotronus.features.certificate_request;

import com.usv.technotronus.features.certificate_request.dto.CertificateRequestApprovalRequest;
import com.usv.technotronus.features.certificate_request.dto.CertificateRequestBySecretaryDto;
import com.usv.technotronus.features.certificate_request.dto.CreateCertificateRequestDto;
import com.usv.technotronus.features.certificate_request.dto.StudentCertificateRequestViewDto;
import com.usv.technotronus.features.pdf_generator.PdfGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/certificate-requests")
public class CertificateRequestController {

    private final CertificateRequestService service;
    private final PdfGeneratorService pdfGeneratorService;

    @PostMapping
    public ResponseEntity<CreateCertificateRequestDto> createCertificateRequest(@RequestBody CreateCertificateRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createCertificateRequest(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CertificateRequest> getCertificateRequestById(@PathVariable Integer id) {
        CertificateRequest certificateRequest = service.getCertificateRequestById(id);
        if (certificateRequest == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(certificateRequest);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CertificateRequestBySecretaryDto> updateCertificateRequestApprovalStatus(@PathVariable Integer id,
                                                                                                   @RequestBody CertificateRequestApprovalRequest approvalRequest) {
        CertificateRequestBySecretaryDto updatedRequest = service.updateCertificateRequestApprovalStatus(id, approvalRequest.getStatus());
        if (updatedRequest == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedRequest);
    }

    @GetMapping("/secretary/{secretaryId}")
    public List<CertificateRequestBySecretaryDto> getAllCertificateRequestsBySecretaryAssigned(@PathVariable UUID secretaryId) {

        return service.getAllCertificateRequestsBySecretaryAssigned(secretaryId);
    }

    @GetMapping("/student/{studentId}")
    public List<StudentCertificateRequestViewDto> getAllCertificateRequestsByStudent(@PathVariable UUID studentId) {

        return service.getAllCertificateRequestsByStudent(studentId);
    }

    @PostMapping("/generate-certificate/{certificateRequestId}")
    public void generateCertificate(@PathVariable(name = "certificateRequestId") Integer certificateRequestId) {

        pdfGeneratorService.generatePdf("Cosmin",
            2023,
            "program",
            "de aia",
            42, LocalDate.now(),
            "Test",
            "Test",
            "IF",
            "fara taxa");
    }
}
