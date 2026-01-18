package com.sisuz.organization.controller;

import com.sisuz.organization.common.api.ApiResponse;
import com.sisuz.organization.model.request.UserCreateRequest;
import com.sisuz.organization.model.request.UserUpdateRequest;
import com.sisuz.organization.model.response.UserResponse;
import com.sisuz.organization.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserController {

    private static final String TENANT_HEADER = "Tenant-ID";
    private static final String COMPANY_HEADER = "Company-ID";

    private final UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponse<UserResponse>> create(
            @RequestHeader(TENANT_HEADER) UUID tenantId,
            @RequestHeader(COMPANY_HEADER) UUID companyId,
            @Valid @RequestBody UserCreateRequest req
    ) {
        UserResponse data = userService.create(tenantId, companyId, req);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("User created", data));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserResponse>> get(
            @RequestHeader(TENANT_HEADER) UUID tenantId,
            @RequestHeader(COMPANY_HEADER) UUID companyId,
            @PathVariable UUID userId
    ) {
        UserResponse data = userService.get(tenantId, companyId, userId);
        return ResponseEntity.ok(ApiResponse.success(data));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<UserResponse>>> list(
            @RequestHeader(TENANT_HEADER) UUID tenantId,
            @RequestHeader(COMPANY_HEADER) UUID companyId,
            @RequestParam(name = "isActive", required = false, defaultValue = "false") Boolean isActive,
            @RequestParam(name = "partnerId", required = false) Long partnerId,
            Pageable pageable
    ) {
        Page<UserResponse> data = userService.list(tenantId, companyId, isActive, partnerId, pageable);
        return ResponseEntity.ok(ApiResponse.success(data));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserResponse>> update(
            @RequestHeader(TENANT_HEADER) UUID tenantId,
            @RequestHeader(COMPANY_HEADER) UUID companyId,
            @PathVariable UUID userId,
            @Valid @RequestBody UserUpdateRequest req
    ) {
        UserResponse data = userService.update(tenantId, companyId, userId, req);
        return ResponseEntity.ok(ApiResponse.success("User updated", data));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse<Object>> delete(
            @RequestHeader(TENANT_HEADER) UUID tenantId,
            @RequestHeader(COMPANY_HEADER) UUID companyId,
            @PathVariable UUID userId
    ) {
        userService.delete(tenantId, companyId, userId);
        return ResponseEntity.ok(ApiResponse.success("User deleted", null));
    }
}
