package com.usv.technotronus.features.certificate_request;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CertificateRequestRepository extends JpaRepository<CertificateRequest, Integer> {

    List<CertificateRequest> findAllBySecretaryId(UUID secretrayId);

    List<CertificateRequest> findAllByUserId(UUID userId);

}
