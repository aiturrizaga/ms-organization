package com.sisuz.organization.feature.tenant.repository;

import com.sisuz.organization.feature.tenant.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TenantRepository extends JpaRepository<Tenant, UUID> {
    Optional<Tenant> findBySlug(String slug);

    boolean existsBySlug(String slug);

    boolean existsByDomain(String domain);

    boolean existsBySubdomain(String subdomain);
}
