package com.sisuz.organization.feature.user.controller;

import com.sisuz.organization.common.api.ApiResponse;
import com.sisuz.organization.feature.user.controller.dto.UserCreateRequest;
import com.sisuz.organization.feature.user.controller.dto.UserFilter;
import com.sisuz.organization.feature.user.controller.dto.UserResponse;
import com.sisuz.organization.feature.user.controller.dto.UserUpdateRequest;
import com.sisuz.organization.feature.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserController {

    private final UserService service;

    @PostMapping
    public ApiResponse<UserResponse> create(@Valid @RequestBody UserCreateRequest request) {
        return ApiResponse.success("User created", service.create(request));
    }

    @GetMapping("/{id}")
    public ApiResponse<UserResponse> getById(@PathVariable UUID id) {
        return ApiResponse.success(service.getById(id));
    }

    @GetMapping
    public ApiResponse<Page<UserResponse>> getAll(Pageable pageable, @ModelAttribute UserFilter filter) {
        return ApiResponse.success(service.getAll(pageable, filter));
    }

    @PutMapping("/{id}")
    public ApiResponse<UserResponse> update(@PathVariable UUID id, @Valid @RequestBody UserUpdateRequest request) {
        return ApiResponse.success("User updated", service.update(id, request));
    }

    @PatchMapping("/{id}/deactivate")
    public ApiResponse<Void> deactivate(@PathVariable UUID id) {
        service.deactivate(id);
        return ApiResponse.success("User deactivated", null);
    }

    @PatchMapping("/{id}/activate")
    public ApiResponse<Void> activate(@PathVariable UUID id) {
        service.activate(id);
        return ApiResponse.success("User activated", null);
    }

    @PostMapping("/{id}/company/assign")
    public ApiResponse<Void> assignCompany(@PathVariable UUID id) {
        service.assignCompany(id);
        return ApiResponse.success("Company assigned to user", null);
    }

    @PostMapping("/{id}/company/unassign")
    public ApiResponse<Void> unassignCompany(@PathVariable UUID id) {
        service.unassignCompany(id);
        return ApiResponse.success("Company unassigned from user", null);
    }
}
