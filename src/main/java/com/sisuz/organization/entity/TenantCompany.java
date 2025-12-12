package com.sisuz.organization.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tenant_company")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TenantCompany {

    @EmbeddedId
    private TenantCompanyId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("tenantId")
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("companyId")
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    public static TenantCompany of(Tenant tenant, Company company, Boolean isActive) {
        return TenantCompany.builder()
                .id(new TenantCompanyId(tenant.getId(), company.getId()))
                .tenant(tenant)
                .company(company)
                .isActive(isActive)
                .build();
    }
}
