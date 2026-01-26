package com.sisuz.organization.feature.tenant.mapper;

import com.sisuz.organization.feature.tenant.entity.Tenant;
import com.sisuz.organization.feature.tenant.controller.dto.TenantCreateRequest;
import com.sisuz.organization.feature.tenant.controller.dto.TenantUpdateRequest;
import com.sisuz.organization.feature.tenant.controller.dto.TenantResponse;
import org.mapstruct.*;


@Mapper(componentModel = "spring")
public interface TenantMapper {

    TenantResponse toResponse(Tenant entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", constant = "true")
    Tenant toEntity(TenantCreateRequest req);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", ignore = true)
    void updateEntity(@MappingTarget Tenant entity, TenantUpdateRequest req);

}
