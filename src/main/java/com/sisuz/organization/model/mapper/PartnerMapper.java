package com.sisuz.organization.model.mapper;

import com.sisuz.organization.entity.Company;
import com.sisuz.organization.entity.Partner;
import com.sisuz.organization.entity.Tenant;
import com.sisuz.organization.model.request.PartnerRequest;
import com.sisuz.organization.model.response.PartnerResponse;
import org.springframework.stereotype.Component;

@Component
public class PartnerMapper {

    public Partner toEntity(PartnerRequest request, Tenant tenant, Company company) {
        return Partner.builder()
                .tenant(tenant)
                .company(company)
                .code(request.code())
                .name(request.name())
                .lastname(request.lastname())
                .legalName(request.legalName())
                .documentTypeId(request.documentTypeId())
                .documentNumber(request.documentNumber())
                .email(request.email())
                .phone(request.phone())
                .mobile(request.mobile())
                .address(request.address())
                .addressReference(request.addressReference())
                .ubigeoId(request.ubigeoId())
                .isCompany(request.isCompany() != null ? request.isCompany() : Boolean.FALSE)
                .isActive(request.isActive() != null ? request.isActive() : Boolean.TRUE)
                .build();
    }

    public void updateEntity(PartnerRequest request, Partner partner, Tenant tenant, Company company) {
        partner.setTenant(tenant);
        partner.setCompany(company);
        partner.setCode(request.code());
        partner.setName(request.name());
        partner.setLastname(request.lastname());
        partner.setLegalName(request.legalName());
        partner.setDocumentTypeId(request.documentTypeId());
        partner.setDocumentNumber(request.documentNumber());
        partner.setEmail(request.email());
        partner.setPhone(request.phone());
        partner.setMobile(request.mobile());
        partner.setAddress(request.address());
        partner.setAddressReference(request.addressReference());
        partner.setUbigeoId(request.ubigeoId());
        if (request.isCompany() != null) {
            partner.setIsCompany(request.isCompany());
        }
        if (request.isActive() != null) {
            partner.setIsActive(request.isActive());
        }
    }

    public PartnerResponse toDto(Partner partner) {
        return new PartnerResponse(
                partner.getId(),
                partner.getTenant().getId(),
                partner.getCompany().getId(),
                partner.getCode(),
                partner.getName(),
                partner.getLastname(),
                partner.getLegalName(),
                partner.getDocumentTypeId(),
                partner.getDocumentNumber(),
                partner.getEmail(),
                partner.getPhone(),
                partner.getMobile(),
                partner.getAddress(),
                partner.getAddressReference(),
                partner.getUbigeoId(),
                partner.getIsCompany(),
                partner.getIsActive()
        );
    }
}
