package com.sisuz.organization.feature.store.controller.dto;

import java.util.UUID;

public record StoreResponse(
        UUID id,
        UUID companyId,
        String name,
        Integer addressId,
        String email,
        String phone,
        Integer state,
        boolean active
) {
}
