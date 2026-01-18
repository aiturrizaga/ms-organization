package com.sisuz.organization.repository;

import com.sisuz.organization.entity.Partner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PartnerRepository extends JpaRepository<Partner, Long> {
    Optional<Partner> findByIdAndTenantIdAndCompanyId(Long id, UUID tenantId, UUID companyId);

    Page<Partner> findByTenantIdAndCompanyId(UUID tenantId, UUID companyId, Pageable pageable);

    Page<Partner> findByTenantIdAndCompanyIdAndActive(UUID tenantId, UUID companyId, Boolean active, Pageable pageable);

    boolean existsByTenantIdAndCompanyIdAndDocumentTypeIdAndDocumentNumber(
            UUID tenantId, UUID companyId, Integer documentTypeId, String documentNumber
    );

    boolean existsByTenantIdAndCompanyIdAndDocumentTypeIdAndDocumentNumberAndIdNot(
            UUID tenantId, UUID companyId, Integer documentTypeId, String documentNumber, Long id
    );
}
