package com.usv.technotronus.features.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findUserByEmailContainsIgnoreCase(String email);

    List<User> findUserByDomainId(Integer domainId);

    @Query(value = "SELECT sp.name AS studyProgramName, COUNT(*) AS studentsNumber " +
        "FROM account a " +
        "JOIN study_program sp ON a.study_program_id = sp.id " +
        "WHERE a.domain_id = :domainId " +
        "GROUP BY sp.name", nativeQuery = true)
    List<TotalStudentPerStudyProgramResult> getUserNumberPerStudyProgram(@Param("domainId") Long domainId);


    @Query(value = "SELECT COUNT(*) " +
        "FROM account s " +
        "WHERE s.domain_id = :domainId", nativeQuery = true)
    Integer getTotalStudentsByDomain(Long domainId);

    List<User> findUsersByRole(UserRole role);
}
