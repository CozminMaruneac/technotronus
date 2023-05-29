package com.usv.technotronus.features.academic_year;

import com.usv.technotronus.features.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "academic_year")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AcademicYear {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String currentAcademicYear;

    @ManyToOne
    @JoinColumn(name = "dean_id")
    private User dean;

    @ManyToOne
    @JoinColumn(name = "chief_secretary_id")
    private User chiefSecretary;

    @Enumerated(EnumType.STRING)
    private Status status;
}
