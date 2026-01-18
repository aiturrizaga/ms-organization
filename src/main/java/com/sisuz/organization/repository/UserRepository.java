package com.sisuz.organization.repository;

import com.sisuz.organization.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByIdAndTenantIdAndCompanyId(UUID id, UUID tenantId, UUID companyId);

    Page<User> findByTenantIdAndCompanyId(UUID tenantId, UUID companyId, Pageable pageable);

    Page<User> findByTenantIdAndCompanyIdAndActive(UUID tenantId, UUID companyId, boolean active, Pageable pageable);

    Page<User> findByTenantIdAndCompanyIdAndPartnerId(UUID tenantId, UUID companyId, Long partnerId, Pageable pageable);

    Page<User> findByTenantIdAndCompanyIdAndPartnerIdAndActive(
            UUID tenantId, UUID companyId, Long partnerId, boolean active, Pageable pageable
    );

    boolean existsByTenantIdAndCompanyIdAndEmail(UUID tenantId, UUID companyId, String email);

    boolean existsByTenantIdAndCompanyIdAndEmailAndIdNot(UUID tenantId, UUID companyId, String email, UUID id);
}
