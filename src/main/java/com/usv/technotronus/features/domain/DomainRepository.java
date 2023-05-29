package com.usv.technotronus.features.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DomainRepository extends JpaRepository<Domain, Integer> {

    Domain findByName(String name);
}
