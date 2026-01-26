package com.sisuz.organization.security.context;

import java.util.UUID;

public final class CompanyContext {

    private static final ThreadLocal<UUID> COMPANY_ID = new ThreadLocal<>();

    private CompanyContext() {
    }

    public static void setCompanyId(UUID companyId) {
        COMPANY_ID.set(companyId);
    }

    public static UUID getCompanyId() {
        return COMPANY_ID.get();
    }

    public static void clear() {
        COMPANY_ID.remove();
    }
}
