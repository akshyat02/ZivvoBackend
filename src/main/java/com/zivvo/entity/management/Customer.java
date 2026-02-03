package com.zivvo.entity.management;

import com.zivvo.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "customers")
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class Customer extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull @Column(length = 15, unique = true)
    private String phone;

    @Column(length = 100, unique = true)
    private String email;

    @Column(length = 100)
    private String name;

    @Column(columnDefinition = "bigint[]")
    private List<Long> brands;

    @Column(name = "total_orders")
    private Integer totalOrders = 0;

    @Column(name = "lifetime_value", precision = 12, scale = 2)
    private BigDecimal lifetimeValue = BigDecimal.ZERO;
}
