package com.sisuz.organization.repository;

import com.sisuz.organization.entity.Partner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PartnerRepository extends JpaRepository<Partner, Long> {
    Page<Partner> findByTenantId(UUID tenantId, Pageable pageable);

    Page<Partner> findByCompanyId(UUID companyId, Pageable pageable);

    Optional<Partner> findByTenantIdAndCompanyIdAndDocumentTypeIdAndDocumentNumber(
            UUID tenantId,
            UUID companyId,
            Integer documentTypeId,
            String documentNumberId
    );
}
