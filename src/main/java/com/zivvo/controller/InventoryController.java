package com.zivvo.controller;

import com.zivvo.entity.business.Inventory;
import com.zivvo.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/brands/{brandId}/stores/{storeId}/inventory")
@RequiredArgsConstructor
@Slf4j
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping("/variants/{variantId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Inventory createInventory( @PathVariable Long brandId, @PathVariable Long storeId, @PathVariable Long variantId, @RequestParam(defaultValue = "0") BigDecimal availableQty) {
        return inventoryService.createInventory(storeId, variantId, availableQty);
    }

    @GetMapping
    public List<Inventory> getInventory( @PathVariable Long storeId, @RequestParam(required = false) Long variantId, @RequestParam(required = false) BigDecimal minQty) {
        return inventoryService.getInventoryByStoreId(storeId, variantId, minQty);
    }

    @PostMapping("/variants/{variantId}/stock")
    public ResponseEntity<String> updateStock( @PathVariable Long storeId, @PathVariable Long variantId, @RequestParam BigDecimal qtyChange) {
        boolean updated = inventoryService.updateStock(storeId, variantId, qtyChange);
        return updated ? ResponseEntity.ok("Stock updated") : ResponseEntity.notFound().build();
    }
}
