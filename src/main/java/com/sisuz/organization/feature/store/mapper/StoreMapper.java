package com.sisuz.organization.feature.store.mapper;

import com.sisuz.organization.feature.store.controller.dto.StoreCreateRequest;
import com.sisuz.organization.feature.store.controller.dto.StoreResponse;
import com.sisuz.organization.feature.store.controller.dto.StoreUpdateRequest;
import com.sisuz.organization.feature.store.entity.Store;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface StoreMapper {

    @Mapping(target = "companyId", source = "company.id")
    StoreResponse toResponse(Store entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", constant = "true")
    @Mapping(target = "company", ignore = true)
    Store toEntity(StoreCreateRequest req);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "company", ignore = true)
    void updateEntity(@MappingTarget Store entity, StoreUpdateRequest req);

}
