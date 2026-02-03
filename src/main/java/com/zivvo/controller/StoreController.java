package com.zivvo.controller;

import com.zivvo.dto.StoreDTO;
import com.zivvo.entity.core.Store;
import com.zivvo.service.StoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brands/{brandId}/stores")
@RequiredArgsConstructor
@Slf4j
public class StoreController {

    private final StoreService storeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Store createStore(@PathVariable Long brandId,@Valid @RequestBody StoreDTO dto) {
        log.info("Creating store for brand {}: {}", brandId, dto.getCode());
        return storeService.createStore(brandId, dto);
    }

    @GetMapping
    public List<Store> getStoresByBrand(@PathVariable Long brandId,@RequestParam(required = false) Boolean isActive,@RequestParam(required = false) String storeType) {
        log.info("Fetching stores for brand {} (active={}, type={})", brandId, isActive, storeType);
        return storeService.getStoresByBrandId(brandId, isActive, storeType);
    }

    @GetMapping("/{storeId}")
    public ResponseEntity<Store> getStore(@PathVariable Long brandId,@PathVariable Long storeId) {
        log.info("Fetching store {}/{}", brandId, storeId);
        return storeService.getStore(brandId, storeId)    .map(ResponseEntity::ok)    .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<Store> getStoreByCode(@PathVariable Long brandId,@PathVariable String code) {
        log.info("Fetching store for brand {} code: {}", brandId, code);
        return storeService.getStoreByCode(brandId, code)    .map(ResponseEntity::ok)    .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{storeId}")
    public ResponseEntity<Store> updateStore(@PathVariable Long brandId,@PathVariable Long storeId,@Valid @RequestBody(required = false) StoreDTO dto,@RequestParam(required = false) String storeType) {
        log.info("Updating store {}/{}", brandId, storeId);
        return storeService.updateStore(brandId, storeId, dto, storeType)    .map(ResponseEntity::ok)    .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{storeId}")
    public ResponseEntity<String> deleteStore(@PathVariable Long brandId,@PathVariable Long storeId,@RequestParam(defaultValue = "false") boolean softDelete) {
        log.info("Deleting store {}/{} (soft={})", brandId, storeId, softDelete);
        boolean deleted = storeService.deleteStore(brandId, storeId);
        if (deleted) {return ResponseEntity.ok(softDelete ? "Soft deleted" : "Deleted");
        }
        return ResponseEntity.notFound().build();
    }
}
