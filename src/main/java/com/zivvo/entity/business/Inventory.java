package com.zivvo.entity.business;

import com.zivvo.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "inventory",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_store_variant",
                columnNames = {"store_id", "variant_id"}
        ))
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class Inventory extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long storeId;

    @NotNull
    private Long variantId;

    @Column(name = "available_qty", precision = 10, scale = 3)
    private BigDecimal availableQty = BigDecimal.ZERO;

    @Column(name = "sold_qty", precision = 10, scale = 3)
    private BigDecimal soldQty = BigDecimal.ZERO;

}
