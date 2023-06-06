package com.usv.technotronus.features.certificate_request.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CertificateRequestReportDto {

    private Integer createdThisMonth;
    private Integer acceptedThisMonth;
    private Integer refusedThisMonth;
}
