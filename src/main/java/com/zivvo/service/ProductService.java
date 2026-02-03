package com.zivvo.service;

import com.zivvo.dto.ProductDTO;
import com.zivvo.entity.product.Product;
import com.zivvo.repository.BrandRepository;
import com.zivvo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;

    @Transactional
    public Product createProduct(Long brandId, ProductDTO dto) {
        brandRepository.findById(brandId).orElseThrow(() ->
                new IllegalArgumentException("Brand not found: " + brandId));

        Product product = Product.builder()
                .brandId(brandId)
                .name(dto.getName())
                .description(dto.getDescription())
                .category(dto.getCategory())
                .thumbnailUrl(dto.getThumbnailUrl())
                .imageUrls(dto.getImageUrls())
                .tags(dto.getTags())
                .unitType(dto.getUnitType())
                .build();

        return productRepository.save(product);
    }

    public List<Product> getProductsByBrandId(Long brandId, String category) {
        if (category != null) {
            return productRepository.findByBrandIdAndCategory(brandId, category);
        }
        return productRepository.findByBrandId(brandId);
    }

    public Optional<Product> getProduct(Long brandId, Long productId) {
        return productRepository.findByIdAndBrandId(productId, brandId);
    }

    @Transactional
    public Optional<Product> updateProduct(Long brandId, Long productId, ProductDTO dto, String category) {
        return productRepository.findByIdAndBrandId(productId, brandId).map(product -> {
            if (dto != null) {
                product.setName(dto.getName());
                product.setDescription(dto.getDescription());
                product.setThumbnailUrl(dto.getThumbnailUrl());
            }
            if (category != null) {
                product.setCategory(category);
            }
            return productRepository.save(product);
        });
    }

    @Transactional
    public boolean deleteProduct(Long brandId, Long productId) {
        return productRepository.findByIdAndBrandId(productId, brandId)
                .map(product -> {
                    productRepository.delete(product);
                    return true;
                }).orElse(false);
    }
}
