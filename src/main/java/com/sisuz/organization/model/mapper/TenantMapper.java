package com.sisuz.organization.model.mapper;

import com.sisuz.organization.entity.Tenant;
import com.sisuz.organization.model.request.TenantRequest;
import com.sisuz.organization.model.response.TenantResponse;
import org.springframework.stereotype.Component;


@Component
public class TenantMapper {

    public TenantResponse toDto(Tenant tenant) {
        return new TenantResponse(
                tenant.getId(),
                tenant.getName(),
                tenant.getDomain(),
                tenant.getSubdomain(),
                tenant.getSlug(),
                tenant.getSettings(),
                tenant.getPlanId(),
                tenant.getIsActive()
        );
    }

    public Tenant toEntity(TenantRequest request) {
        return Tenant.builder()
                .name(request.name())
                .domain(request.domain())
                .subdomain(request.subdomain())
                .slug(request.slug())
                .settings(request.settings())
                .planId(request.planId())
                .isActive(request.isActive() != null ? request.isActive() : Boolean.TRUE)
                .build();
    }

}
