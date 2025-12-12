package com.sisuz.organization.service.impl;

import com.sisuz.organization.entity.Company;
import com.sisuz.organization.entity.Tenant;
import com.sisuz.organization.entity.TenantCompany;
import com.sisuz.organization.entity.TenantCompanyId;
import com.sisuz.organization.exception.NotFoundException;
import com.sisuz.organization.model.mapper.TenantCompanyMapper;
import com.sisuz.organization.model.request.TenantCompanyRequest;
import com.sisuz.organization.model.response.TenantCompanyResponse;
import com.sisuz.organization.repository.CompanyRepository;
import com.sisuz.organization.repository.TenantCompanyRepository;
import com.sisuz.organization.repository.TenantRepository;
import com.sisuz.organization.service.TenantCompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TenantCompanyServiceImpl implements TenantCompanyService {

    private final TenantCompanyRepository tenantCompanyRepository;
    private final TenantRepository tenantRepository;
    private final CompanyRepository companyRepository;
    private final TenantCompanyMapper tenantCompanyMapper;

    @Override
    public TenantCompanyResponse create(TenantCompanyRequest request) {
        UUID tenantId = request.tenantId();
        UUID companyId = request.companyId();

        Tenant tenant = tenantRepository.findById(tenantId)
                .orElseThrow(() -> new NotFoundException("Tenant not found:" + tenantId));

        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new NotFoundException("Company not found:" + companyId));

        if (tenantCompanyRepository.existsByTenantIdAndCompanyId(tenantId, companyId)) {
            throw new IllegalArgumentException("Relation Tenant-Company already exists");
        }

        TenantCompany entity = TenantCompany.of(tenant, company, request.isActive());
        return tenantCompanyMapper.toDto(tenantCompanyRepository.save(entity));
    }

    @Override
    public Page<TenantCompanyResponse> getByTenant(UUID tenantId, Pageable pageable) {
        return tenantCompanyRepository.findByTenantId(tenantId, pageable)
                .map(tenantCompanyMapper::toDto);
    }

    @Override
    public TenantCompanyResponse getById(UUID tenantId, UUID companyId) {
        TenantCompanyId id = new TenantCompanyId(tenantId, companyId);
        TenantCompany entity = tenantCompanyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("TenantCompany not found"));
        return tenantCompanyMapper.toDto(entity);
    }
}
