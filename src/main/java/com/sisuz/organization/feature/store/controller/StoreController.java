package com.sisuz.organization.feature.store.controller;

import com.sisuz.organization.common.api.ApiResponse;
import com.sisuz.organization.feature.store.controller.dto.StoreCreateRequest;
import com.sisuz.organization.feature.store.controller.dto.StoreFilter;
import com.sisuz.organization.feature.store.controller.dto.StoreResponse;
import com.sisuz.organization.feature.store.controller.dto.StoreUpdateRequest;
import com.sisuz.organization.feature.store.service.StoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/stores")
public class StoreController {

    private final StoreService storeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<StoreResponse> create(@Valid @RequestBody StoreCreateRequest request) {
        return ApiResponse.success("Store created", storeService.create(request));
    }

    @GetMapping("/{id}")
    public ApiResponse<StoreResponse> getById(@PathVariable UUID id) {
        return ApiResponse.success(storeService.getById(id));
    }

    @GetMapping
    public ApiResponse<Page<StoreResponse>> getAll(Pageable pageable, @ModelAttribute StoreFilter filter) {
        return ApiResponse.success(storeService.getAll(pageable, filter));
    }

    @PutMapping("/{id}")
    public ApiResponse<StoreResponse> update(@PathVariable UUID id, @Valid @RequestBody StoreUpdateRequest request) {
        return ApiResponse.success("Store updated", storeService.update(id, request));
    }

    @DeleteMapping("/{id}/deactivate")
    public ApiResponse<Void> deactivate(@PathVariable UUID id) {
        storeService.deactivate(id);
        return ApiResponse.success("Store deactivated", null);
    }

    @PatchMapping("/{id}/activate")
    public ApiResponse<Void> activate(@PathVariable UUID id) {
        storeService.activate(id);
        return ApiResponse.success("Store activated", null);
    }
}
