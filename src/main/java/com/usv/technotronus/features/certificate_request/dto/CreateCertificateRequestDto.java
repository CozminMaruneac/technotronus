package com.usv.technotronus.features.certificate_request.dto;

import com.usv.technotronus.features.certificate_request.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateCertificateRequestDto {
    private UUID userId;
    private String reason;
    @JsonIgnore
    @Enumerated(EnumType.STRING)
    private Status status = Status.NEW;

    @JsonIgnore
    private UUID secretaryId;

    @JsonIgnore
    private LocalDate requestedDate = LocalDate.now();
}
