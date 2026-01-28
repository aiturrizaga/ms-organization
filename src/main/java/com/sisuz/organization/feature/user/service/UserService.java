package com.sisuz.organization.feature.user.service;

import com.sisuz.organization.feature.user.controller.dto.UserCreateRequest;
import com.sisuz.organization.feature.user.controller.dto.UserFilter;
import com.sisuz.organization.feature.user.controller.dto.UserResponse;
import com.sisuz.organization.feature.user.controller.dto.UserUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UserService {
    UserResponse create(UserCreateRequest request);

    UserResponse getById(UUID id);

    Page<UserResponse> getAll(Pageable pageable, UserFilter filter);

    UserResponse update(UUID id, UserUpdateRequest request);

    void deactivate(UUID id);

    void activate(UUID id);

    void assignCompany(UUID userId);

    void unassignCompany(UUID userId);
}
