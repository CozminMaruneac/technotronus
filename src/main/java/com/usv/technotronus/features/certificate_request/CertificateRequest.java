package com.usv.technotronus.features.certificate_request;

import com.usv.technotronus.features.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "certificate_request")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CertificateRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String reason;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "secretary_id")
    private User secretary;

    private Integer requestNumber;
    private LocalDate requestedDate;
}
