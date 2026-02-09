package com.sisuz.organization.feature.user.service;

import com.sisuz.organization.feature.user.controller.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UserService {
    UserResponse create(UserCreateRequest request);

    UserResponse getById(UUID id);

    UserSessionResponse getSession(String userId);

    Page<UserResponse> getAll(Pageable pageable, UserFilter filter);

    UserResponse update(UUID id, UserUpdateRequest request);

    void deactivate(UUID id);

    void activate(UUID id);

    void assignCompany(UUID userId);

    void unassignCompany(UUID userId);
}
