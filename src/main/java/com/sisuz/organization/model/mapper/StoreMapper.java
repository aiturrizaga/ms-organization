package com.sisuz.organization.model.mapper;

import com.sisuz.organization.entity.Company;
import com.sisuz.organization.entity.Store;
import com.sisuz.organization.model.request.StoreRequest;
import com.sisuz.organization.model.response.StoreResponse;
import org.springframework.stereotype.Component;

@Component
public class StoreMapper {

    public Store toEntity(StoreRequest request, Company company) {
        return Store.builder()
                .company(company)
                .name(request.name())
                .addressId(request.addressId())
                .phone(request.phone())
                .email(request.email())
                .state(request.state())
                .isActive(request.isActive() != null ? request.isActive() : Boolean.TRUE)
                .build();
    }

    public void updateEntity(StoreRequest request, Store store, Company company) {
        store.setCompany(company);
        store.setName(request.name());
        store.setAddressId(request.addressId());
        store.setPhone(request.phone());
        store.setEmail(request.email());
        store.setState(request.state());
        if (request.isActive() != null) {
            store.setIsActive(request.isActive());
        }
    }

    public StoreResponse toDto(Store store) {
        return new StoreResponse(
                store.getId(),
                store.getCompany().getId(),
                store.getName(),
                store.getAddressId(),
                store.getPhone(),
                store.getEmail(),
                store.getState(),
                store.getIsActive()
        );
    }
}
