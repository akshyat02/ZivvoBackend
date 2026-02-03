package com.zivvo.repository;

import com.zivvo.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByBrandId(Long brandId);
    Optional<Product> findByIdAndBrandId(Long productId, Long brandId);
    List<Product> findByBrandIdAndCategory(Long brandId, String category);
}
