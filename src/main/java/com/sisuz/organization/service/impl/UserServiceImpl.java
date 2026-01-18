package com.sisuz.organization.service.impl;

import com.sisuz.organization.entity.Company;
import com.sisuz.organization.entity.Partner;
import com.sisuz.organization.entity.Tenant;
import com.sisuz.organization.entity.User;
import com.sisuz.organization.exception.NotFoundException;
import com.sisuz.organization.model.mapper.UserMapper;
import com.sisuz.organization.model.request.UserCreateRequest;
import com.sisuz.organization.model.request.UserUpdateRequest;
import com.sisuz.organization.model.response.UserResponse;
import com.sisuz.organization.repository.UserRepository;
import com.sisuz.organization.service.UserService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final EntityManager entityManager;

    @Override
    public UserResponse create(UUID tenantId, UUID companyId, UserCreateRequest req) {

        if (userRepository.existsByTenantIdAndCompanyIdAndEmail(tenantId, companyId, req.email())) {
            throw new IllegalStateException("Email already exists for this company");
        }

        User entity = userMapper.toEntity(req);

        entity.setTenant(entityManager.getReference(Tenant.class, tenantId));
        entity.setCompany(entityManager.getReference(Company.class, companyId));
        entity.setPartner(entityManager.getReference(Partner.class, req.partnerId()));

        User saved = userRepository.save(entity);
        return userMapper.toResponse(saved);
    }

    @Override
    public UserResponse update(UUID tenantId, UUID companyId, UUID userId, UserUpdateRequest req) {
        User entity = userRepository.findByIdAndTenantIdAndCompanyId(userId, tenantId, companyId)
                .orElseThrow(() -> NotFoundException.of("User", userId));

        if (req.email() != null && userRepository.existsByTenantIdAndCompanyIdAndEmailAndIdNot(tenantId, companyId, req.email(), userId)) {
            throw new IllegalStateException("Email already exists for this company");
        }

        userMapper.updateEntity(req, entity);

        if (req.partnerId() != null) {
            entity.setPartner(entityManager.getReference(Partner.class, req.partnerId()));
        }

        if (req.tourEnabled() != null) {
            entity.setTourEnabled(req.tourEnabled());
        }

        User saved = userRepository.save(entity);
        return userMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse get(UUID tenantId, UUID companyId, UUID userId) {
        User entity = userRepository.findByIdAndTenantIdAndCompanyId(userId, tenantId, companyId)
                .orElseThrow(() -> NotFoundException.of("User", userId));
        return userMapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserResponse> list(UUID tenantId, UUID companyId, Boolean isActive, Long partnerId, Pageable pageable) {

        Page<User> page;
        boolean hasPartner = partnerId != null;

        if (hasPartner && isActive) {
            page = userRepository.findByTenantIdAndCompanyIdAndPartnerIdAndActive(tenantId, companyId, partnerId, isActive, pageable);
        } else if (hasPartner) {
            page = userRepository.findByTenantIdAndCompanyIdAndPartnerId(tenantId, companyId, partnerId, pageable);
        } else if (isActive != null) {
            page = userRepository.findByTenantIdAndCompanyIdAndActive(tenantId, companyId, isActive, pageable);
        } else {
            page = userRepository.findByTenantIdAndCompanyId(tenantId, companyId, pageable);
        }

        return page.map(userMapper::toResponse);
    }

    @Override
    public void delete(UUID tenantId, UUID companyId, UUID userId) {
        User entity = userRepository.findByIdAndTenantIdAndCompanyId(userId, tenantId, companyId)
                .orElseThrow(() -> NotFoundException.of("User", userId));

        userRepository.delete(entity);
    }
}
