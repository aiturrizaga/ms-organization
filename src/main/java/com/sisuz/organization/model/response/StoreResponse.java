package com.sisuz.organization.model.response;

import java.util.UUID;

public record StoreResponse(
        UUID id,
        UUID companyId,
        String name,
        Integer addressId,
        String phone,
        String email,
        Integer state,
        Boolean isActive
) {}
