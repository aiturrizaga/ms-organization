package com.sisuz.organization.feature.store.service;

import com.sisuz.organization.common.exception.BusinessException;
import com.sisuz.organization.common.exception.NotFoundException;
import com.sisuz.organization.feature.company.repository.CompanyRepository;
import com.sisuz.organization.feature.store.controller.dto.StoreCreateRequest;
import com.sisuz.organization.feature.store.controller.dto.StoreFilter;
import com.sisuz.organization.feature.store.controller.dto.StoreResponse;
import com.sisuz.organization.feature.store.controller.dto.StoreUpdateRequest;
import com.sisuz.organization.feature.store.entity.Store;
import com.sisuz.organization.feature.store.mapper.StoreMapper;
import com.sisuz.organization.feature.store.repository.StoreRepository;
import com.sisuz.organization.feature.store.repository.spec.StoreSpecification;
import com.sisuz.organization.security.context.CompanyContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final StoreMapper storeMapper;

    private final CompanyRepository companyRepository;

    @Override
    public StoreResponse create(StoreCreateRequest request) {
        if (!companyRepository.existsById(currentCompanyId())) {
            throw new BusinessException(3101, "Company not found with id: " + currentCompanyId());
        }

        Store entity = storeMapper.toEntity(request);
        entity.setActive(true);
        Store saved = storeRepository.save(entity);
        return storeMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public StoreResponse getById(UUID id) {
        Store entity = storeRepository.findById(id).orElseThrow(() -> NotFoundException.of("Store", id));
        return storeMapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<StoreResponse> getAll(Pageable pageable, StoreFilter filter) {
        Specification<Store> spec = StoreSpecification.withFilters(
                filter.active(),
                currentCompanyId(),
                filter.name(),
                filter.email(),
                filter.phone(),
                filter.state(),
                filter.createdDate()
        );
        return storeRepository.findAll(spec, pageable).map(storeMapper::toResponse);
    }

    @Override
    public StoreResponse update(UUID id, StoreUpdateRequest request) {
        Store entity = storeRepository.findById(id).orElseThrow(() -> NotFoundException.of("Store", id));

        if (!companyRepository.existsById(currentCompanyId())) {
            throw new BusinessException(3101, "Company not found with id: " + currentCompanyId());
        }

        storeMapper.updateEntity(entity, request);
        Store saved = storeRepository.save(entity);
        return storeMapper.toResponse(saved);
    }

    @Override
    public void deactivate(UUID id) {
        Store entity = storeRepository.findById(id).orElseThrow(() -> NotFoundException.of("Store", id));
        if (!entity.isActive()) throw new BusinessException(3102, "Store is already inactive");
        entity.setActive(false);
        storeRepository.save(entity);
    }

    @Override
    public void activate(UUID id) {
        Store entity = storeRepository.findById(id).orElseThrow(() -> NotFoundException.of("Store", id));
        if (entity.isActive()) throw new BusinessException(3103, "Store is already active");
        entity.setActive(true);
        storeRepository.save(entity);
    }

    private UUID currentCompanyId() {
        UUID companyId = CompanyContext.getCompanyId();
        if (companyId == null) {
            throw new BusinessException(3100, "Company-Id header is required");
        }
        return companyId;
    }
}
