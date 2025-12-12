package com.sisuz.organization.repository;

import com.sisuz.organization.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TenantRepository extends JpaRepository<Tenant, UUID> {
    boolean existsBySlug(String slug);
}
