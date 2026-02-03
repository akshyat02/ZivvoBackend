package com.zivvo.entity.sales;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long storeId;

    @NotNull
    private Long brandId;

    @NotNull
    private Long posTerminalId;

    private Long customerId;
    private Long couponId;

    @NotNull @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    @NotNull @Column(name = "local_business_date")
    private LocalDate localBusinessDate;

    @NotNull @Column(precision = 10, scale = 2)
    private BigDecimal subtotal;

    @Column(name = "discount_amount", precision = 10, scale = 2)
    private BigDecimal discountAmount = BigDecimal.ZERO;

    @Column(name = "cgst_amount", precision = 10, scale = 2)
    private BigDecimal cgstAmount = BigDecimal.ZERO;

    @Column(name = "sgst_amount", precision = 10, scale = 2)
    private BigDecimal sgstAmount = BigDecimal.ZERO;

    @NotNull @Column(precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @Column(length = 20)
    private String status = "COMPLETED";
}
