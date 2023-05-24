package com.usv.technotronus.features.faculty;

import com.usv.technotronus.features.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "faculty")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String acronym;

    private String name;

    @ManyToOne
    @JoinColumn(name = "dean_id")
    private User dean;

    @ManyToOne
    @JoinColumn(name = "chief_secretary_id")
    private User chiefSecretary;
}
