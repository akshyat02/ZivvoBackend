package com.zivvo.repository;

import com.zivvo.entity.core.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

    List<Store> findByBrandId(Long brandId);
    Optional<Store> findByCode(String code);
    List<Store> findByBrandIdAndIsActiveTrue(Long brandId);

    boolean existsByCodeAndBrandId(String code, Long brandId);

    Optional<Store> findByIdAndBrandId(Long storeId, Long brandId);
    Optional<Store> findByCodeAndBrandId(String code, Long brandId);
    List<Store> findByBrandIdAndIsActiveTrue(Long brandId, Boolean isActive);
    List<Store> findByBrandIdAndStoreType(Long brandId, String storeType);
    List<Store> findByBrandIdAndIsActiveAndStoreType(Long brandId, Boolean isActive, String storeType);

}
