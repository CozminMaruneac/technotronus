package com.usv.technotronus.features.certificate.request;

import com.usv.technotronus.features.certificate.request.dto.CertificateRequestApprovalRequest;
import com.usv.technotronus.features.certificate.request.dto.CreateCertificateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/certificate-requests")
public class CertificateRequestController {

    private final CertificateRequestService service;

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
    public ResponseEntity<CertificateRequest> updateCertificateRequestApprovalStatus(
        @PathVariable Integer id,
        @RequestBody CertificateRequestApprovalRequest approvalRequest
    ) {
        CertificateRequest updatedRequest = service.updateCertificateRequestApprovalStatus(id, approvalRequest.getIsApproved());
        if (updatedRequest == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedRequest);
    }
}
