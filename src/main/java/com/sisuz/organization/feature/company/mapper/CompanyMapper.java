package com.sisuz.organization.feature.company.mapper;

import com.sisuz.organization.common.persistence.IdentityDocumentType;
import com.sisuz.organization.feature.company.controller.dto.CompanyCreateRequest;
import com.sisuz.organization.feature.company.controller.dto.CompanyResponse;
import com.sisuz.organization.feature.company.controller.dto.CompanyUpdateRequest;
import com.sisuz.organization.feature.company.entity.Company;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    @Mapping(target = "documentTypeId", source = "documentType.id")
    CompanyResponse toResponse(Company entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", constant = "true")
    @Mapping(target = "documentType", expression = "java(toIdentityDocumentType(req.documentTypeId()))")
    Company toEntity(CompanyCreateRequest req);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "documentType", expression = "java(toIdentityDocumentType(req.documentTypeId()))")
    void updateEntity(@MappingTarget Company entity, CompanyUpdateRequest req);

    default IdentityDocumentType toIdentityDocumentType(Integer id) {
        if (id == null) return null;
        IdentityDocumentType t = new IdentityDocumentType();
        t.setId(id);
        return t;
    }
}
