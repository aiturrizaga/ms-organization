package com.sisuz.organization.feature.partner.service;

import com.sisuz.organization.common.exception.BusinessException;
import com.sisuz.organization.common.exception.NotFoundException;
import com.sisuz.organization.feature.company.entity.Company;
import com.sisuz.organization.feature.company.repository.CompanyRepository;
import com.sisuz.organization.feature.partner.controller.dto.PartnerCreateRequest;
import com.sisuz.organization.feature.partner.controller.dto.PartnerFilter;
import com.sisuz.organization.feature.partner.controller.dto.PartnerResponse;
import com.sisuz.organization.feature.partner.controller.dto.PartnerUpdateRequest;
import com.sisuz.organization.feature.partner.entity.Partner;
import com.sisuz.organization.feature.partner.mapper.PartnerMapper;
import com.sisuz.organization.feature.partner.repository.PartnerRepository;
import com.sisuz.organization.feature.partner.repository.spec.PartnerSpecification;
import com.sisuz.organization.feature.tenant.entity.Tenant;
import com.sisuz.organization.feature.tenant.repository.TenantRepository;
import com.sisuz.organization.security.context.ContextAwareService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PartnerServiceImpl extends ContextAwareService implements PartnerService {

    private final PartnerRepository partnerRepository;
    private final PartnerMapper partnerMapper;

    private final TenantRepository tenantRepository;
    private final CompanyRepository companyRepository;

    @Override
    public PartnerResponse create(PartnerCreateRequest request) {
        UUID tenantId = currentTenantId();
        UUID companyId = currentCompanyId();

        if (!tenantRepository.existsById(tenantId)) {
            throw new BusinessException(4102, "Tenant not found with id: " + tenantId);
        }
        if (!companyRepository.existsById(companyId)) {
            throw new BusinessException(4103, "Company not found with id: " + companyId);
        }

        Partner entity = partnerMapper.toEntity(request);
        entity.setActive(true);

        Tenant t = new Tenant();
        t.setId(tenantId);
        entity.setTenant(t);

        Company c = new Company();
        c.setId(companyId);
        entity.setCompany(c);

        Partner saved = partnerRepository.save(entity);
        return partnerMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public PartnerResponse getById(Long id) {
        UUID tenantId = currentTenantId();
        UUID companyId = currentCompanyId();

        Partner entity = partnerRepository.findById(id).orElseThrow(() -> NotFoundException.of("Partner", id));

        if (!tenantId.equals(entity.getTenant().getId()) || !companyId.equals(entity.getCompany().getId())) {
            throw new NotFoundException("Partner not found with id: " + id);
        }

        return partnerMapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PartnerResponse> getAll(Pageable pageable, PartnerFilter filter) {
        UUID tenantId = currentTenantId();
        UUID companyId = currentCompanyId();

        Specification<Partner> spec = PartnerSpecification.withFilters(
                tenantId,
                companyId,
                filter.active(),
                filter.type(),
                filter.name(),
                filter.documentNumber(),
                filter.email(),
                filter.importantDate(),
                filter.createdDate()
        );

        return partnerRepository.findAll(spec, pageable).map(partnerMapper::toResponse);
    }

    @Override
    public PartnerResponse update(Long id, PartnerUpdateRequest request) {
        UUID tenantId = currentTenantId();
        UUID companyId = currentCompanyId();

        Partner entity = partnerRepository.findById(id).orElseThrow(() -> NotFoundException.of("Partner", id));

        if (!tenantId.equals(entity.getTenant().getId()) || !companyId.equals(entity.getCompany().getId())) {
            throw new NotFoundException("Partner not found with id: " + id);
        }

        partnerMapper.updateEntity(entity, request);
        Partner saved = partnerRepository.save(entity);
        return partnerMapper.toResponse(saved);
    }

    @Override
    public void deactivate(Long id) {
        UUID tenantId = currentTenantId();
        UUID companyId = currentCompanyId();

        Partner entity = partnerRepository.findById(id).orElseThrow(() -> NotFoundException.of("Partner", id));
        if (!tenantId.equals(entity.getTenant().getId()) || !companyId.equals(entity.getCompany().getId())) {
            throw new NotFoundException("Partner not found with id: " + id);
        }

        if (!entity.isActive()) throw new BusinessException(4104, "Partner is already inactive");
        entity.setActive(false);
        partnerRepository.save(entity);
    }

    @Override
    public void activate(Long id) {
        UUID tenantId = currentTenantId();
        UUID companyId = currentCompanyId();

        Partner entity = partnerRepository.findById(id).orElseThrow(() -> NotFoundException.of("Partner", id));
        if (!tenantId.equals(entity.getTenant().getId()) || !companyId.equals(entity.getCompany().getId())) {
            throw new NotFoundException("Partner not found with id: " + id);
        }

        if (entity.isActive()) throw new BusinessException(4105, "Partner is already active");
        entity.setActive(true);
        partnerRepository.save(entity);
    }
}
