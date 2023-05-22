package com.usv.technotronus.features.certificate.request;

import com.usv.technotronus.features.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    private Boolean isApproved;

}
