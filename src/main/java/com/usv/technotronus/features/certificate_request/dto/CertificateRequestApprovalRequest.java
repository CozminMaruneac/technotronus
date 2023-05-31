package com.usv.technotronus.features.certificate_request.dto;

import com.usv.technotronus.features.certificate_request.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CertificateRequestApprovalRequest {

    private Status status;
}
