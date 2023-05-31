package com.usv.technotronus.features.certificate_request.dto;

import com.usv.technotronus.features.certificate_request.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class StudentCertificateRequestViewDto {

    private Integer id;
    private Status status;
    private LocalDate requestedDate;
    private String reason;
}
