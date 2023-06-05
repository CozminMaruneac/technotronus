package com.usv.technotronus.features.user;

import com.usv.technotronus.features.domain.Domain;
import com.usv.technotronus.features.study_program.StudyProgram;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "account")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id", nullable = false)
    private UUID id;

    private String firstName;

    private String fatherInitial;

    private String lastName;

    private String email;

    private String profileImageUrl;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @ManyToOne
    @JoinColumn(name = "domain_id")
    private Domain domain;

    @ManyToOne
    @JoinColumn(name = "study_program_id")
    private StudyProgram studyProgram;

    private Integer studyYear;

    @Enumerated(EnumType.STRING)
    private FinancialStatus financialStatus;

    public String getFullName(){

        return this.lastName + " " + this.fatherInitial + " " + this.firstName;
    }
}
