package com.zivvo.service;

import com.zivvo.dto.ProductVariantDTO;
import com.zivvo.entity.product.ProductVariant;
import com.zivvo.repository.ProductRepository;
import com.zivvo.repository.ProductVariantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductVariantService {

    private final ProductVariantRepository variantRepository;
    private final ProductRepository productRepository;

    @Transactional
    public ProductVariant createVariant(Long brandId, Long storeId, Long productId, ProductVariantDTO dto) {
        productRepository.findByIdAndBrandId(productId, brandId).orElseThrow(() ->
                new IllegalArgumentException("Product not found: " + productId));

        if (variantRepository.existsBySku(dto.getSku())) {
            throw new IllegalArgumentException("SKU exists: " + dto.getSku());
        }

        ProductVariant variant = ProductVariant.builder()
                .productId(productId)
                .sku(dto.getSku())
                .barcode(dto.getBarcode())
                .size(dto.getSize())
                .color(dto.getColor())
                .mrp(dto.getMrp())
                .basePrice(dto.getBasePrice())
                .gstRate(dto.getGstRate())
                .cgstRate(dto.getCgstRate() != null ? dto.getCgstRate() : BigDecimal.ZERO)
                .sgstRate(dto.getSgstRate() != null ? dto.getCgstRate() : BigDecimal.ZERO)
                .build();

        ProductVariant saved = variantRepository.save(variant);
        log.info("Variant created: productId={}, sku={}", productId, dto.getSku());
        return saved;
    }

    public List<ProductVariant> getVariantsByProductId(Long productId) {
        return variantRepository.findByProductId(productId);
    }

    public Optional<ProductVariant> getVariant(Long productId, Long variantId) {
        return variantRepository.findByIdAndProductId(variantId, productId);
    }
}
