package com.zivvo.controller;

import com.zivvo.dto.BrandDTO;
import com.zivvo.entity.core.Brand;
import com.zivvo.service.BrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/brands")
@RequiredArgsConstructor
@Slf4j
public class BrandController {

    private final BrandService brandService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Brand createBrand(@Valid @RequestBody BrandDTO dto) {
        log.info("Creating brand: {}", dto.getCode());
        return brandService.createBrand(dto);
    }

    @GetMapping
    public List<Brand> getAllBrands(@RequestParam(required = false) String code,@RequestParam(required = false) String businessType) {
        log.info("Fetching brands: code={}, businessType={}", code, businessType);

        List<Brand> brands = brandService.searchBrands(code, businessType);

        log.info("Found {} brands matching filters", brands.size());
        return brands;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Brand> getBrandById(@PathVariable Long id) {
        return brandService.getBrandById(id)    .map(ResponseEntity::ok)    .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<Brand> getBrandByCode(@PathVariable String code) {
        log.info("Fetching brand by code: {}", code);

        Optional<Brand> brand = brandService.getBrandById(1L); // Demo
        return brand.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Brand> updateBrand(@PathVariable Long id,@Valid @RequestBody(required = false) BrandDTO dto,@RequestParam(required = false) String businessType) {
        log.info("Updating brand ID: {}, businessType: {}", id, businessType);
        return brandService.updateBrand(id, dto != null ? dto : new BrandDTO())    .map(ResponseEntity::ok)    .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBrand(@PathVariable Long id,@RequestParam(defaultValue = "false") boolean softDelete) {
        
        log.info("Deleting brand ID: {} (soft={})", id, softDelete);

        boolean deleted = brandService.deleteBrand(id);
        if (deleted) {String message = softDelete ? "Soft deleted" : "Hard deleted";return ResponseEntity.ok(message + " brand: " + id);
        }
        return ResponseEntity.notFound().build();
    }
}
