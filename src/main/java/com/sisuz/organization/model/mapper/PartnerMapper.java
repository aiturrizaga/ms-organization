package com.sisuz.organization.model.mapper;

import com.sisuz.organization.entity.Partner;
import com.sisuz.organization.model.request.CreatePartnerRequest;
import com.sisuz.organization.model.request.UpdatePartnerRequest;
import com.sisuz.organization.model.response.PartnerResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface PartnerMapper {

    @Mapping(target = "tenantId", source = "tenant.id")
    @Mapping(target = "companyId", source = "company.id")
    @Mapping(target = "active", source = "active")
    PartnerResponse toResponse(Partner entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tenant", ignore = true)
    @Mapping(target = "company", ignore = true)
    Partner toEntity(CreatePartnerRequest req);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tenant", ignore = true)
    @Mapping(target = "company", ignore = true)
    @Mapping(target = "active", ignore = true)
    void updateEntity(UpdatePartnerRequest req, @MappingTarget Partner entity);
}
