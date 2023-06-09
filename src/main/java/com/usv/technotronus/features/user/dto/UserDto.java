package com.usv.technotronus.features.user.dto;

import com.usv.technotronus.features.user.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDto {

    private UUID id;

    private String firstName;

    private String fatherInitial;

    private String lastName;

    private String email;

    private String profileImageUrl;

    @JsonIgnore
    private UserRole role = UserRole.USER;

    private String signature;

    private LocalDate dateOfBirth;

    private String personalIdentificationNumber;

    private String address;

    private String phoneNumber;
}
