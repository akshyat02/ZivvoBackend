package com.zivvo.entity.core;

import com.zivvo.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;


@Entity
@Table(name = "stores")
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class Store extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long brandId;

    @NotNull @Column(length = 20)
    private String code;

    @NotNull @Column(length = 100)
    private String name;

    private String address;

    @Column(precision = 10, scale = 8)
    private BigDecimal latitude;

    @Column(precision = 11, scale = 8)
    private BigDecimal longitude;

    @Column(name = "store_type", length = 30)
    private String storeType;

    @Column(name = "is_active")
    private boolean isActive = true;
}
