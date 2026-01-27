package com.sisuz.organization.security.context;

import java.util.UUID;

public record RequestContext(
        UUID tenantId,
        UUID companyId
) {
}
