package com.sisuz.organization.service.impl;

import com.sisuz.organization.entity.Company;
import com.sisuz.organization.entity.Store;
import com.sisuz.organization.exception.NotFoundException;
import com.sisuz.organization.model.mapper.StoreMapper;
import com.sisuz.organization.model.request.StoreRequest;
import com.sisuz.organization.model.response.StoreResponse;
import com.sisuz.organization.repository.CompanyRepository;
import com.sisuz.organization.repository.StoreRepository;
import com.sisuz.organization.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final CompanyRepository companyRepository;
    private final StoreMapper storeMapper;

    @Override
    public StoreResponse create(StoreRequest request) {
        Company company = companyRepository.findById(request.companyId())
                .orElseThrow(() -> new NotFoundException("Company not found: " + request.companyId()));

        Store store = storeMapper.toEntity(request, company);
        return storeMapper.toDto(storeRepository.save(store));
    }

    @Override
    public Page<StoreResponse> getByCompany(UUID companyId, Pageable pageable) {
        return storeRepository.findByCompanyId(companyId, pageable)
                .map(storeMapper::toDto);
    }

    @Override
    public StoreResponse getById(UUID storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new NotFoundException("Store not found: " + storeId));
        return storeMapper.toDto(store);
    }

    @Override
    public StoreResponse update(UUID storeId, StoreRequest request) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new NotFoundException("Store not found: " + storeId));

        Company company = companyRepository.findById(request.companyId())
                .orElseThrow(() -> new NotFoundException("Company not found: " + request.companyId()));

        storeMapper.updateEntity(request, store, company);
        return storeMapper.toDto(storeRepository.save(store));
    }
}
