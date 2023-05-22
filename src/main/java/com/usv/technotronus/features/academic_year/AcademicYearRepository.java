package com.usv.technotronus.features.academic_year;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AcademicYearRepository extends JpaRepository<AcademicYear, Integer> {

    Optional<AcademicYear> findFirstByStatus(Status status);

    Boolean existsAcademicYearByStatus(Status status);
}
