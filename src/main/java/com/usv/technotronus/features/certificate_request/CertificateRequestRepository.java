package com.usv.technotronus.features.certificate_request;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CertificateRequestRepository extends JpaRepository<CertificateRequest, Integer> {

    List<CertificateRequest> findAllBySecretaryId(UUID secretaryId);

    List<CertificateRequest> findAllByUserId(UUID userId);

    @Query(value = "SELECT COUNT(*) FROM certificate_request WHERE EXTRACT(MONTH FROM requested_date) = EXTRACT(MONTH FROM CURRENT_DATE)", nativeQuery = true)
    Integer countCreatedThisMonth();

    @Query(value = "SELECT COUNT(*) FROM certificate_request WHERE EXTRACT(MONTH FROM requested_date) = EXTRACT(MONTH FROM CURRENT_DATE) AND status = 'APPROVED'", nativeQuery = true)
    Integer countAcceptedThisMonth();

    @Query(value = "SELECT COUNT(*) FROM certificate_request WHERE EXTRACT(MONTH FROM requested_date) = EXTRACT(MONTH FROM CURRENT_DATE) AND status = 'REJECTED'", nativeQuery = true)
    Integer countRefusedThisMonth();
}
