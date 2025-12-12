package com.sisuz.organization.controller;

import com.sisuz.organization.model.request.StoreRequest;
import com.sisuz.organization.model.response.StoreResponse;
import com.sisuz.organization.service.StoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/stores")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StoreResponse createStore(@Valid @RequestBody StoreRequest request) {
        return storeService.create(request);
    }

    @GetMapping("/by-company/{companyId}")
    public Page<StoreResponse> getStoresByCompany(@PathVariable UUID companyId, Pageable pageable) {
        return storeService.getByCompany(companyId, pageable);
    }

    @GetMapping("/{storeId}")
    public StoreResponse getStore(@PathVariable UUID storeId) {
        return storeService.getById(storeId);
    }

    @PutMapping("/{storeId}")
    public StoreResponse updateStore(
            @PathVariable UUID storeId,
            @Valid @RequestBody StoreRequest request
    ) {
        return storeService.update(storeId, request);
    }
}
