package com.sisuz.organization.service.impl;

import com.sisuz.organization.entity.Tenant;
import com.sisuz.organization.exception.NotFoundException;
import com.sisuz.organization.model.mapper.TenantMapper;
import com.sisuz.organization.model.request.TenantRequest;
import com.sisuz.organization.model.response.TenantResponse;
import com.sisuz.organization.repository.TenantRepository;
import com.sisuz.organization.service.TenantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TenantServiceImpl implements TenantService {

    private final TenantRepository tenantRepository;
    private final TenantMapper tenantMapper;

    @Override
    public TenantResponse create(TenantRequest request) {
        Tenant tenant = tenantMapper.toEntity(request);
        return tenantMapper.toDto(tenantRepository.save(tenant));
    }

    @Override
    public Page<TenantResponse> getAll(Pageable pageable) {
        return tenantRepository.findAll(pageable)
                .map(tenantMapper::toDto);
    }

    @Override
    public TenantResponse getById(UUID tenantId) {
        Tenant tenant = tenantRepository.findById(tenantId)
                .orElseThrow(() -> new NotFoundException("Tenant not found: " + tenantId));
        return tenantMapper.toDto(tenant);
    }
}
