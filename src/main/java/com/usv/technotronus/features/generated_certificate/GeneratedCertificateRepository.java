package com.usv.technotronus.features.generated_certificate;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface GeneratedCertificateRepository extends JpaRepository<GeneratedCertificate, Integer> {

    List<GeneratedCertificate> findAllByRegistrationDateBetween(LocalDate dateFrom, LocalDate dateTo);
}
