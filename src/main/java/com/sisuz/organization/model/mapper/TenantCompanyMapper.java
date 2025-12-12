package com.sisuz.organization.model.mapper;

import com.sisuz.organization.entity.TenantCompany;
import com.sisuz.organization.model.response.TenantCompanyResponse;
import org.springframework.stereotype.Component;

@Component
public class TenantCompanyMapper {

    public TenantCompanyResponse toDto(TenantCompany entity) {
        return new TenantCompanyResponse(
                entity.getId().getTenantId(),
                entity.getId().getCompanyId(),
                entity.getIsActive()
        );
    }
}
