package com.sisuz.organization.model.mapper;

import com.sisuz.organization.entity.User;
import com.sisuz.organization.model.request.UserCreateRequest;
import com.sisuz.organization.model.request.UserUpdateRequest;
import com.sisuz.organization.model.response.UserResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "tenantId", source = "tenant.id")
    @Mapping(target = "companyId", source = "company.id")
    @Mapping(target = "partnerId", source = "partner.id")
    @Mapping(target = "active", source = "active")
    UserResponse toResponse(User entity);

    @Mapping(target = "tenant", ignore = true)
    @Mapping(target = "company", ignore = true)
    @Mapping(target = "partner", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(
            target = "tourEnabled",
            expression = "java(req.tourEnabled() != null ? req.tourEnabled() : true)"
    )
    User toEntity(UserCreateRequest req);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tenant", ignore = true)
    @Mapping(target = "company", ignore = true)
    @Mapping(target = "partner", ignore = true)
    @Mapping(target = "active", ignore = true)
    void updateEntity(UserUpdateRequest req, @MappingTarget User entity);
}
