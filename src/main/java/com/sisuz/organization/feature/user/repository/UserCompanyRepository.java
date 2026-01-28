package com.sisuz.organization.feature.user.repository;

import com.sisuz.organization.feature.user.entity.UserCompany;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserCompanyRepository extends JpaRepository<UserCompany, Long> {

    List<UserCompany> findAllByAppUserId(UUID userId);

    Optional<UserCompany> findByAppUserIdAndTenantIdAndCompanyId(UUID userId, UUID tenantId, UUID companyId);
}
