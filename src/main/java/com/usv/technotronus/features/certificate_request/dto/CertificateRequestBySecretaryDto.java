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
public class CertificateRequestBySecretaryDto {

    private Integer id;
    private String studentName;
    private String studentEmail;
    private String studyProgram;
    private Status status;

}
