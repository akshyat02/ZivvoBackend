package com.zivvo.service;

import com.zivvo.dto.StoreDTO;
import com.zivvo.entity.core.Brand;
import com.zivvo.entity.core.Store;
import com.zivvo.repository.BrandRepository;
import com.zivvo.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class StoreService {

    private final StoreRepository storeRepository;
    private final BrandRepository brandRepository;

    @Transactional
    public Store createStore(Long brandId, StoreDTO dto) {
        brandRepository.findById(brandId).orElseThrow(() ->
                new IllegalArgumentException("Brand not found: " + brandId));

        if (storeRepository.existsByCodeAndBrandId(dto.getCode(), brandId)) {
            throw new IllegalArgumentException("Store code exists for brand");
        }

        Store store = Store.builder()
                .brandId(brandId)
                .code(dto.getCode())
                .name(dto.getName())
                .address(dto.getAddress())
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .storeType(dto.getStoreType())
                .isActive(dto.getIsActive() != null ? dto.getIsActive() : true)
                .build();

        Store saved = storeRepository.save(store);
        log.info("Store created: {}/{}", brandId, saved.getId());
        return saved;
    }

    public List<Store> getStoresByBrandId(Long brandId, Boolean isActive, String storeType) {
        if (isActive != null && storeType != null) {
            return storeRepository.findByBrandIdAndIsActiveAndStoreType(brandId, isActive, storeType);
        } else if (isActive != null) {
            return storeRepository.findByBrandIdAndIsActiveTrue(brandId, isActive);
        } else if (storeType != null) {
            return storeRepository.findByBrandIdAndStoreType(brandId, storeType);
        }
        return storeRepository.findByBrandId(brandId);
    }

    public Optional<Store> getStore(Long brandId, Long storeId) {
        return storeRepository.findByIdAndBrandId(storeId, brandId);
    }

    public Optional<Store> getStoreByCode(Long brandId, String code) {
        return storeRepository.findByCodeAndBrandId(code, brandId);
    }

    @Transactional
    public Optional<Store> updateStore(Long brandId, Long storeId, StoreDTO dto, String storeType) {
        return storeRepository.findByIdAndBrandId(storeId, brandId).map(store -> {
            if (dto != null) {
                store.setCode(dto.getCode());
                store.setName(dto.getName());
                store.setAddress(dto.getAddress());
            }
            if (storeType != null) {
                store.setStoreType(storeType);
            }
            return storeRepository.save(store);
        });
    }

    @Transactional
    public boolean deleteStore(Long brandId, Long storeId) {
        return storeRepository.findByIdAndBrandId(storeId, brandId)
                .map(store -> {
                    storeRepository.delete(store);
                    return true;
                }).orElse(false);
    }
}
