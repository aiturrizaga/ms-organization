package com.sisuz.organization.repository;

import com.sisuz.organization.entity.TenantCompany;
import com.sisuz.organization.entity.TenantCompanyId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TenantCompanyRepository extends JpaRepository<TenantCompany, TenantCompanyId> {
    Page<TenantCompany> findByTenantId(UUID tenantId, Pageable pageable);

    List<TenantCompany> findByCompanyId(UUID companyId);

    boolean existsByTenantIdAndCompanyId(UUID tenantId, UUID companyId);
}
