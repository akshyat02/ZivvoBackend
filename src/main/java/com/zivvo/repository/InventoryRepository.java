package com.zivvo.repository;

import com.zivvo.entity.business.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    List<Inventory> findByStoreId(Long storeId);
    Optional<Inventory> findByStoreIdAndVariantId(Long storeId, Long variantId);
    boolean existsByStoreIdAndVariantId(Long storeId, Long variantId);
    List<Inventory> findByStoreIdAndAvailableQtyGreaterThanEqual(Long storeId, BigDecimal minQty);
}
