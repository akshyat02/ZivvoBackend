package com.zivvo.service;

import com.zivvo.dto.request.CreateBrandRequest;
import com.zivvo.dto.response.BrandResponse;
import com.zivvo.entity.core.Brand;
import com.zivvo.repository.BrandRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class SuperAdminService {

    @Autowired
    private final BrandRepository brandRepository;

    @Transactional
    public BrandResponse createBrand(CreateBrandRequest request) {
        if (brandRepository.existsByCode(request.getCode())) {
            throw new IllegalArgumentException("Brand code exists: " + request.getCode());
        }
        Brand brand = Brand.builder()
                .code(request.getCode().toUpperCase())
                .name(request.getName())
                .businessType(request.getBusinessType())
                .industry(request.getIndustry())
                .currencyCode("INR")
                .defaultGstRate(BigDecimal.valueOf(18.00))
                .build();

        Brand saved = brandRepository.save(brand);
        return BrandResponse.builder()
                .id(saved.getId())
                .code(saved.getCode())
                .name(saved.getName())
                .businessType(saved.getBusinessType())
                .industry(saved.getIndustry())
                .currencyCode(saved.getCurrencyCode())
                .defaultGstRate(saved.getDefaultGstRate())
                .createdAt(saved.getCreatedAt())
                .build();

    }


}
