package com.zivvo.controller;

import com.zivvo.dto.ProductVariantDTO;
import com.zivvo.entity.product.ProductVariant;
import com.zivvo.service.ProductVariantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/brands/{brandId}/stores/{storeId}/products/{productId}/variants")
@RequiredArgsConstructor
@Slf4j
public class ProductVariantController {

    private final ProductVariantService productVariantService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductVariant createVariant(@PathVariable Long brandId, @PathVariable Long storeId, @PathVariable Long productId, @Valid @RequestBody ProductVariantDTO dto) {
        return productVariantService.createVariant(brandId, storeId, productId, dto);
    }
}
