package com.sisuz.organization.repository;

import com.sisuz.organization.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID> {
    boolean existsByRuc(String ruc);

    Optional<Company> findByRuc(String ruc);
}
