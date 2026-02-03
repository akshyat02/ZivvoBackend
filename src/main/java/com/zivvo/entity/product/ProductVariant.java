package com.zivvo.entity.product;

import com.zivvo.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "product_variants")
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class ProductVariant extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long productId;

    @NotNull @Column(unique = true, length = 50)
    private String sku;

    @Column(unique = true, length = 50)
    private String barcode;

    @Column(length = 20)
    private String size;

    @Column(length = 20)
    private String color;

    @Column(length = 50)
    private String material;

    @NotNull @Column(precision = 10, scale = 2)
    private BigDecimal mrp;

    @NotNull @Column(name = "base_price", precision = 10, scale = 2)
    private BigDecimal basePrice;

    @NotNull @Column(name = "gst_rate", precision = 5, scale = 2)
    private BigDecimal gstRate;

    @Column(name = "cgst_rate", precision = 5, scale = 2)
    private BigDecimal cgstRate;

    @Column(name = "sgst_rate", precision = 5, scale = 2)
    private BigDecimal sgstRate;
}