package com.usv.technotronus.features.certificate.request.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CertificateRequestApprovalRequest {

    private Boolean isApproved;
}
