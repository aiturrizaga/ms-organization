package com.sisuz.organization.service.impl;

import com.sisuz.organization.entity.Company;
import com.sisuz.organization.entity.Partner;
import com.sisuz.organization.entity.Tenant;
import com.sisuz.organization.exception.BusinessException;
import com.sisuz.organization.exception.NotFoundException;
import com.sisuz.organization.model.mapper.PartnerMapper;
import com.sisuz.organization.model.request.CreatePartnerRequest;
import com.sisuz.organization.model.request.PartnerRequest;
import com.sisuz.organization.model.request.UpdatePartnerRequest;
import com.sisuz.organization.model.response.PartnerResponse;
import com.sisuz.organization.repository.PartnerRepository;
import com.sisuz.organization.service.PartnerService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PartnerServiceImpl implements PartnerService {

    private final PartnerRepository partnerRepository;
    private final PartnerMapper partnerMapper;
    private final EntityManager entityManager;

    @Override
    public PartnerResponse create(UUID tenantId, UUID companyId, CreatePartnerRequest req) {

        if (req.documentTypeId() != null && req.documentNumber() != null) {
            boolean exists = partnerRepository.existsByTenantIdAndCompanyIdAndDocumentTypeIdAndDocumentNumber(
                    tenantId, companyId, req.documentTypeId(), req.documentNumber()
            );
            if (exists) {
                throw new BusinessException(500, "Partner with same document already exists");
            }
        }

        Partner entity = partnerMapper.toEntity(req);

        entity.setTenant(entityManager.getReference(Tenant.class, tenantId));
        entity.setCompany(entityManager.getReference(Company.class, companyId));

        Partner saved = partnerRepository.save(entity);
        return partnerMapper.toResponse(saved);
    }

    @Override
    public PartnerResponse update(UUID tenantId, UUID companyId, Long partnerId, UpdatePartnerRequest req) {
        Partner entity = partnerRepository.findByIdAndTenantIdAndCompanyId(partnerId, tenantId, companyId)
                .orElseThrow(() -> NotFoundException.of("Partner", partnerId));

        if (req.documentTypeId() != null && req.documentNumber() != null) {
            boolean exists = partnerRepository.existsByTenantIdAndCompanyIdAndDocumentTypeIdAndDocumentNumberAndIdNot(
                    tenantId, companyId, req.documentTypeId(), req.documentNumber(), partnerId
            );
            if (exists) {
                throw new IllegalStateException("Partner with same document already exists");
            }
        }

        partnerMapper.updateEntity(req, entity);

        Partner saved = partnerRepository.save(entity);
        return partnerMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public PartnerResponse get(UUID tenantId, UUID companyId, Long partnerId) {
        Partner entity = partnerRepository.findByIdAndTenantIdAndCompanyId(partnerId, tenantId, companyId)
                .orElseThrow(() -> NotFoundException.of("Partner", partnerId));
        return partnerMapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PartnerResponse> list(UUID tenantId, UUID companyId, Boolean isActive, Pageable pageable) {
        Page<Partner> page = isActive != null
                ? partnerRepository.findByTenantIdAndCompanyIdAndActive(tenantId, companyId, isActive, pageable)
                : partnerRepository.findByTenantIdAndCompanyId(tenantId, companyId, pageable);

        return page.map(partnerMapper::toResponse);
    }

    @Override
    public void delete(UUID tenantId, UUID companyId, Long partnerId) {
        Partner entity = partnerRepository.findByIdAndTenantIdAndCompanyId(partnerId, tenantId, companyId)
                .orElseThrow(() -> NotFoundException.of("Partner", partnerId));
        partnerRepository.delete(entity);
    }
}
