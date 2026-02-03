package com.zivvo.entity.business;

import com.zivvo.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import com.zivvo.entity.enums.CouponType;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "coupons")
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class Coupon extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull @Column(unique = true, length = 20)
    private String code;

    private Long brandId;  // NULL = global

    @Column(length = 100)
    private String name;

    @NotNull @Enumerated(EnumType.STRING)
    private CouponType type;

    @NotNull @Column(precision = 10, scale = 2)
    private BigDecimal value;

    @Column(name = "max_discount", precision = 10, scale = 2)
    private BigDecimal maxDiscount;

    @Column(name = "min_cart_value", precision = 10, scale = 2)
    private BigDecimal minCartValue;

    @Column(name = "valid_from")
    private LocalDate validFrom;

    @Column(name = "valid_to")
    private LocalDate validTo;

    @Column(name = "usage_limit")
    private Integer usageLimit;

    @Column(name = "used_count")
    private Integer usedCount = 0;
}