package com.sisuz.organization.feature.tenant.service;

import com.sisuz.organization.feature.tenant.entity.Tenant;
import com.sisuz.organization.common.exception.BusinessException;
import com.sisuz.organization.common.exception.NotFoundException;
import com.sisuz.organization.feature.tenant.mapper.TenantMapper;
import com.sisuz.organization.feature.tenant.controller.dto.TenantCreateRequest;
import com.sisuz.organization.feature.tenant.controller.dto.TenantUpdateRequest;
import com.sisuz.organization.feature.tenant.controller.dto.TenantResponse;
import com.sisuz.organization.feature.tenant.repository.TenantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TenantServiceImpl implements TenantService {

    private final TenantRepository repository;
    private final TenantMapper tenantMapper;

    @Override
    public TenantResponse create(TenantCreateRequest request) {
        validateUniqueOnCreate(request.slug(), request.domain(), request.subdomain());

        Tenant entity = tenantMapper.toEntity(request);
        Tenant saved = repository.save(entity);
        return tenantMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public TenantResponse getById(UUID id) {
        Tenant entity = repository.findById(id).orElseThrow(() -> NotFoundException.of("Tenant", id));
        return tenantMapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TenantResponse> getAll(Pageable pageable) {
        Page<Tenant> page = repository.findAll(pageable);
        return page.map(tenantMapper::toResponse);
    }

    @Override
    public TenantResponse update(UUID id, TenantUpdateRequest request) {
        Tenant entity = repository.findById(id).orElseThrow(() -> NotFoundException.of("Tenant", id));

        validateUniqueOnUpdate(id, request.slug(), request.domain(), request.subdomain());

        tenantMapper.updateEntity(entity, request);
        Tenant saved = repository.save(entity);
        return tenantMapper.toResponse(saved);
    }

    @Override
    public void deactivate(UUID id) {
        Tenant entity = repository.findById(id)
                .orElseThrow(() -> NotFoundException.of("Tenant", id));

        if (!entity.isActive()) {
            throw new BusinessException(1101, "Tenant is already inactive");
        }

        entity.setActive(false);
        repository.save(entity);
    }

    @Override
    public void activate(UUID id) {
        Tenant entity = repository.findById(id)
                .orElseThrow(() -> NotFoundException.of("Tenant", id));

        if (entity.isActive()) {
            throw new BusinessException(1102, "Tenant is already active");
        }

        entity.setActive(true);
        repository.save(entity);
    }

    private void validateUniqueOnCreate(String slug, String domain, String subdomain) {
        if (slug != null && repository.existsBySlug(slug)) {
            throw new BusinessException(1001, "Slug already exists: " + slug);
        }
        if (domain != null && repository.existsByDomain(domain)) {
            throw new BusinessException(1002, "Domain already exists: " + domain);
        }
        if (subdomain != null && repository.existsBySubdomain(subdomain)) {
            throw new BusinessException(1003, "Subdomain already exists: " + subdomain);
        }
    }

    private void validateUniqueOnUpdate(UUID id, String slug, String domain, String subdomain) {
        if (slug != null) {
            repository.findBySlug(slug).ifPresent(found -> {
                if (!found.getId().equals(id)) throw new BusinessException(1001, "Slug already exists: " + slug);
            });
        }
        if (domain != null) {
            if (repository.existsByDomain(domain)) {
                throw new BusinessException(1002, "Domain already exists: " + domain);
            }
        }
        if (subdomain != null) {
            if (repository.existsBySubdomain(subdomain)) {
                throw new BusinessException(1003, "Subdomain already exists: " + subdomain);
            }
        }
    }
}
