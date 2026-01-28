package com.sisuz.organization.feature.user.mapper;

import com.sisuz.organization.feature.partner.entity.Partner;
import com.sisuz.organization.feature.user.controller.dto.UserCompanyResponse;
import com.sisuz.organization.feature.user.controller.dto.UserCreateRequest;
import com.sisuz.organization.feature.user.controller.dto.UserResponse;
import com.sisuz.organization.feature.user.controller.dto.UserUpdateRequest;
import com.sisuz.organization.feature.user.entity.User;
import com.sisuz.organization.feature.user.entity.UserCompany;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "partnerId", source = "partner.id")
    @Mapping(target = "companies", ignore = true) // se setea en service
    UserResponse toResponse(User entity);

    UserCompanyResponse toCompanyResponse(UserCompany entity);

    List<UserCompanyResponse> toCompanyResponseList(List<UserCompany> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", constant = "true")
    @Mapping(target = "partner", expression = "java(toPartner(req.partnerId()))")
    @Mapping(target = "tourEnabled", expression = "java(req.tourEnabled() != null ? req.tourEnabled() : Boolean.TRUE)")
    User toEntity(UserCreateRequest req);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "partner", ignore = true)
    void updateEntity(@MappingTarget User entity, UserUpdateRequest req);

    default Partner toPartner(Long id) {
        if (id == null) return null;
        Partner p = new Partner();
        p.setId(id);
        return p;
    }
}
