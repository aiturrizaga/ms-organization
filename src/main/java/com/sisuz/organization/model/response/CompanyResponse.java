package com.sisuz.organization.model.response;

import java.util.UUID;

public record CompanyResponse(
        UUID id,
        String legalName,
        String tradeName,
        String ruc,
        Long logoFileId,
        String legalOwner,
        Integer state,
        Boolean isActive
) {
}
