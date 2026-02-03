package com.zivvo.service;

import com.zivvo.dto.BrandDTO;
import com.zivvo.entity.core.Brand;
import com.zivvo.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BrandService {

    private final BrandRepository brandRepository;

    @Transactional
    public Brand createBrand(BrandDTO dto) {
        if (brandRepository.findByCode(dto.getCode()).isPresent()) {
            throw new IllegalArgumentException("Brand code already exists: " + dto.getCode());
        }

        Brand brand = Brand.builder()
                .code(dto.getCode())
                .name(dto.getName())
                .businessType(dto.getBusinessType())
                .currencyCode(dto.getCurrencyCode())
                .defaultGstRate(dto.getDefaultGstRate())
                .build();

        Brand savedBrand = brandRepository.save(brand);
        log.info("Brand created successfully: ID={}, Code={}", savedBrand.getId(), savedBrand.getCode());
        return savedBrand;
    }

    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    public Optional<Brand> getBrandById(Long id) {
        return brandRepository.findById(id);
    }

    @Transactional
    public Optional<Brand> updateBrand(Long id, BrandDTO dto) {
        return brandRepository.findById(id).map(brand -> {
            brand.setCode(dto.getCode());
            brand.setName(dto.getName());
            brand.setBusinessType(dto.getBusinessType());
            brand.setCurrencyCode(dto.getCurrencyCode());
            brand.setDefaultGstRate(dto.getDefaultGstRate());

            Brand updatedBrand = brandRepository.save(brand);
            log.info("Brand updated successfully: ID={}", id);
            return updatedBrand;
        });
    }

    @Transactional
    public boolean deleteBrand(Long id) {
        return brandRepository.findById(id)
                .map(brand -> {
                    brandRepository.delete(brand);
                    log.info("Brand deleted successfully: ID={}", id);
                    return true;
                })
                .orElse(false);
    }

    public List<Brand> searchBrands(String code, String businessType) {
        return brandRepository.findByCodeContainingAndBusinessTypeContaining(
                code != null ? code : "",
                businessType != null ? businessType : "");
    }
}
