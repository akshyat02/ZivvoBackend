package com.zivvo.service;


import com.zivvo.entity.business.Inventory;
import com.zivvo.repository.InventoryRepository;
import com.zivvo.repository.ProductVariantRepository;
import com.zivvo.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final StoreRepository storeRepository;
    private final ProductVariantRepository variantRepository;

    @Transactional
    public Inventory createInventory(Long storeId, Long variantId, BigDecimal availableQty) {
        storeRepository.findById(storeId).orElseThrow(() ->
                new IllegalArgumentException("Store not found: " + storeId));
        variantRepository.findById(variantId).orElseThrow(() ->
                new IllegalArgumentException("Variant not found: " + variantId));

        if (inventoryRepository.existsByStoreIdAndVariantId(storeId, variantId)) {
            throw new IllegalArgumentException("Inventory exists for store/variant");
        }

        Inventory inventory = Inventory.builder()
                .storeId(storeId)
                .variantId(variantId)
                .availableQty(availableQty != null ? availableQty : BigDecimal.ZERO)
                .soldQty(BigDecimal.ZERO)
                .build();

        Inventory saved = inventoryRepository.save(inventory);
        log.info("Inventory created: storeId={}, variantId={}, qty={}", storeId, variantId, availableQty);
        return saved;
    }

    public List<Inventory> getInventoryByStoreId(Long storeId, Long variantId, BigDecimal minQty) {
        if (variantId != null) {
            Optional<Inventory> inventoryOpt = inventoryRepository.findByStoreIdAndVariantId(storeId, variantId);
            return inventoryOpt.map(inv -> Collections.singletonList(inv))
                    .orElse(Collections.emptyList());
        }
        if (minQty != null) {
            return inventoryRepository.findByStoreIdAndAvailableQtyGreaterThanEqual(storeId, minQty);
        }
        return inventoryRepository.findByStoreId(storeId);
    }



    @Transactional
    public boolean updateStock(Long storeId, Long variantId, BigDecimal qtyChange) {
        return inventoryRepository.findByStoreIdAndVariantId(storeId, variantId).map(inventory -> {
            inventory.setAvailableQty(inventory.getAvailableQty().add(qtyChange));
            inventory.setSoldQty(inventory.getSoldQty().add(qtyChange.negate()));
            inventoryRepository.save(inventory);
            return true;
        }).orElse(false);
    }

    @Transactional
    public Optional<Inventory> getInventory(Long storeId, Long variantId) {
        return inventoryRepository.findByStoreIdAndVariantId(storeId, variantId);
    }
}

