package com.sisuz.organization.service.impl;

import com.sisuz.organization.entity.Company;
import com.sisuz.organization.entity.Partner;
import com.sisuz.organization.entity.Tenant;
import com.sisuz.organization.exception.NotFoundException;
import com.sisuz.organization.model.mapper.PartnerMapper;
import com.sisuz.organization.model.request.PartnerRequest;
import com.sisuz.organization.model.response.PartnerResponse;
import com.sisuz.organization.repository.CompanyRepository;
import com.sisuz.organization.repository.PartnerRepository;
import com.sisuz.organization.repository.TenantRepository;
import com.sisuz.organization.service.PartnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PartnerServiceImpl implements PartnerService {

    private final PartnerRepository partnerRepository;
    private final TenantRepository tenantRepository;
    private final CompanyRepository companyRepository;
    private final PartnerMapper partnerMapper;

    @Override
    public PartnerResponse create(PartnerRequest request) {
        Tenant tenant = tenantRepository.findById(request.tenantId())
                .orElseThrow(() -> new NotFoundException("Tenant not found: " + request.tenantId()));

        Company company = companyRepository.findById(request.companyId())
                .orElseThrow(() -> new NotFoundException("Company not found: " + request.companyId()));

        Partner partner = partnerMapper.toEntity(request, tenant, company);
        return partnerMapper.toDto(partnerRepository.save(partner));
    }

    @Override
    public Page<PartnerResponse> getByTenant(UUID tenantId, Pageable pageable) {
        return partnerRepository.findByTenantId(tenantId, pageable)
                .map(partnerMapper::toDto);
    }

    @Override
    public Page<PartnerResponse> getByCompany(UUID companyId, Pageable pageable) {
        return partnerRepository.findByCompanyId(companyId, pageable)
                .map(partnerMapper::toDto);
    }

    @Override
    public PartnerResponse getById(Long partnerId) {
        Partner partner = partnerRepository.findById(partnerId)
                .orElseThrow(() -> new NotFoundException("Partner not found: " + partnerId));
        return partnerMapper.toDto(partner);
    }

    @Override
    public PartnerResponse update(Long partnerId, PartnerRequest request) {
        Partner partner = partnerRepository.findById(partnerId)
                .orElseThrow(() -> new NotFoundException("Partner not found: " + partnerId));

        Tenant tenant = tenantRepository.findById(request.tenantId())
                .orElseThrow(() -> new NotFoundException("Tenant not found: " + request.tenantId()));

        Company company = companyRepository.findById(request.companyId())
                .orElseThrow(() -> new NotFoundException("Company not found: " + request.companyId()));

        partnerMapper.updateEntity(request, partner, tenant, company);
        return partnerMapper.toDto(partnerRepository.save(partner));
    }
}
