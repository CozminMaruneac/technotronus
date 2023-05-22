package com.usv.technotronus.features.certificate.request.dto;

import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateCertificateRequestDto {
    private UUID userId;
    private String reason;
    @JsonIgnore
    private Boolean isApproved = false;
}
