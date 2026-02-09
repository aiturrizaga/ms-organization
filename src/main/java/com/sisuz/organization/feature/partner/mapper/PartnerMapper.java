package com.sisuz.organization.feature.partner.mapper;

import com.sisuz.organization.common.persistence.IdentityDocumentType;
import com.sisuz.organization.feature.company.entity.Company;
import com.sisuz.organization.feature.partner.entity.Partner;
import com.sisuz.organization.feature.partner.controller.dto.PartnerCreateRequest;
import com.sisuz.organization.feature.partner.controller.dto.PartnerResponse;
import com.sisuz.organization.feature.partner.controller.dto.PartnerUpdateRequest;
import com.sisuz.organization.feature.tenant.entity.Tenant;
import org.mapstruct.*;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface PartnerMapper {

    @Mapping(target = "tenantId", source = "tenant.id")
    @Mapping(target = "companyId", source = "company.id")
    @Mapping(target = "documentTypeId", source = "documentType.id")
    PartnerResponse toResponse(Partner entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", constant = "true")
    @Mapping(target = "tenant", ignore = true)
    @Mapping(target = "company", ignore = true)
    @Mapping(target = "documentType", expression = "java(toDocType(req.documentTypeId()))")
    Partner toEntity(PartnerCreateRequest req);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "tenant", ignore = true)
    @Mapping(target = "company", ignore = true)
    @Mapping(target = "documentType", ignore = true)
    void updateEntity(@MappingTarget Partner entity, PartnerUpdateRequest req);

    default IdentityDocumentType toDocType(Integer id) {
        if (id == null) return null;
        IdentityDocumentType t = new IdentityDocumentType();
        t.setId(id);
        return t;
    }

    default Tenant toTenant(UUID id) {
        if (id == null) return null;
        Tenant t = new Tenant();
        t.setId(id);
        return t;
    }

    default Company toCompany(UUID id) {
        if (id == null) return null;
        Company c = new Company();
        c.setId(id);
        return c;
    }
}
