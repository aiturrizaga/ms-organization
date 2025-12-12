package com.sisuz.organization.model.response;

import java.util.UUID;

public record PartnerResponse(
        Long id,
        UUID tenantId,
        UUID companyId,
        String code,
        String name,
        String lastname,
        String legalName,
        Integer documentTypeId,
        String documentNumber,
        String email,
        String phone,
        String mobile,
        String address,
        String addressReference,
        String ubigeoId,
        Boolean isCompany,
        Boolean isActive
) {
}
