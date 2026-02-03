package com.zivvo.controller;

import com.zivvo.dto.ProductDTO;
import com.zivvo.entity.product.Product;
import com.zivvo.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/brands/{brandId}/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@PathVariable Long brandId, @Valid @RequestBody ProductDTO dto) {
        log.info("Creating product for brand {}: {}", brandId, dto.getName());
        return productService.createProduct(brandId, dto);
    }

    @GetMapping
    public List<Product> getProducts(@PathVariable Long brandId,@RequestParam(required = false) String category) {
        log.info("Fetching products for brand {} (category={})", brandId, category);
        return productService.getProductsByBrandId(brandId, category);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Long brandId, @PathVariable Long productId) {
        return productService.getProduct(brandId, productId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long brandId,@PathVariable Long productId,@Valid @RequestBody(required = false) ProductDTO dto,@RequestParam(required = false) String category) {
        return productService.updateProduct(brandId, productId, dto, category).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long brandId,@PathVariable Long productId,@RequestParam(defaultValue = "false") boolean softDelete) {
        boolean deleted = productService.deleteProduct(brandId, productId);
        if (deleted) {return ResponseEntity.ok(softDelete ? "Soft deleted" : "Deleted");
        }
        return ResponseEntity.notFound().build();
    }
}
