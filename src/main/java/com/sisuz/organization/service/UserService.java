package com.sisuz.organization.service;

import com.sisuz.organization.model.request.UserCreateRequest;
import com.sisuz.organization.model.request.UserUpdateRequest;
import com.sisuz.organization.model.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UserService {
    UserResponse create(UUID tenantId, UUID companyId, UserCreateRequest req);

    UserResponse update(UUID tenantId, UUID companyId, UUID userId, UserUpdateRequest req);

    UserResponse get(UUID tenantId, UUID companyId, UUID userId);

    Page<UserResponse> list(UUID tenantId, UUID companyId, Boolean isActive, Long partnerId, Pageable pageable);

    void delete(UUID tenantId, UUID companyId, UUID userId);
}
