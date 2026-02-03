package com.zivvo.repository;

import com.zivvo.entity.core.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    Optional<Brand> findByCode(String code);
    boolean existsByCode(String code);

    // SuperAdmin analytics queries
    @Query("SELECT COUNT(b) FROM Brand b")
    Long countTotalBrands();

    @Query("SELECT COUNT(s) FROM Store s")
    Long countTotalStores();

    List<Brand> findAllByOrderByCreatedAtDesc();

    @Query("SELECT b FROM Brand b WHERE LOWER(b.name) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(b.code) LIKE LOWER(CONCAT('%', :search, '%'))")
    List<Brand> searchBrands(@Param("search") String search);

    List<Brand> findByCodeContainingAndBusinessTypeContaining(String code, String businessType);
}