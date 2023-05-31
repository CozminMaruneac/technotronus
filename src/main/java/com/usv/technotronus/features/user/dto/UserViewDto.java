package com.usv.technotronus.features.user.dto;

import com.usv.technotronus.features.user.FinancialStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserViewDto {

    private UUID id;

    private String name;

    private String email;

    private String studyProgram;

    private String studyDomain;

    private Integer studyYear;

    @Enumerated(EnumType.STRING)
    private FinancialStatus financialStatus;

}