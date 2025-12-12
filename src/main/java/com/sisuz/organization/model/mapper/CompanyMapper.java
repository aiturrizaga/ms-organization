package com.sisuz.organization.model.mapper;

import com.sisuz.organization.entity.Company;
import com.sisuz.organization.model.request.CompanyRequest;
import com.sisuz.organization.model.response.CompanyResponse;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

    public Company toEntity(CompanyRequest request) {
        return Company.builder()
                .legalName(request.legalName())
                .tradeName(request.tradeName())
                .ruc(request.ruc())
                .logoFileId(request.logoFileId())
                .legalOwner(request.legalOwner())
                .legalAddressId(request.legalAddressId())
                .billingAddressId(request.billingAddressId())
                .state(request.state())
                .isActive(request.isActive() != null ? request.isActive() : Boolean.TRUE)
                .build();
    }

    public void updateEntity(CompanyRequest request, Company company) {
        company.setLegalName(request.legalName());
        company.setTradeName(request.tradeName());
        company.setRuc(request.ruc());
        company.setLogoFileId(request.logoFileId());
        company.setLegalOwner(request.legalOwner());
        company.setLegalAddressId(request.legalAddressId());
        company.setBillingAddressId(request.billingAddressId());
        company.setState(request.state());
        if (request.isActive() != null) {
            company.setIsActive(request.isActive());
        }
    }

    public CompanyResponse toDto(Company company) {
        return new CompanyResponse(
                company.getId(),
                company.getLegalName(),
                company.getTradeName(),
                company.getRuc(),
                company.getLogoFileId(),
                company.getLegalOwner(),
                company.getLegalAddressId(),
                company.getIsActive()
        );
    }
}
