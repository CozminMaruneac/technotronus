package com.usv.technotronus.features.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
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

    private String signature;

    private LocalDate dateOfBirth;

    private String personalIdentificationNumber;

    private String address;

    private String phoneNumber;

}
