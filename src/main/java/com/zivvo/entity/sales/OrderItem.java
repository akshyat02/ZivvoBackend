package com.zivvo.entity.sales;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class OrderItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long orderId;

    @NotNull
    private Long variantId;

    @NotNull @Column(precision = 10, scale = 3)
    private BigDecimal qty;

    @NotNull @Column(name = "unit_price", precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @NotNull @Column(precision = 10, scale = 2)
    private BigDecimal mrp;

    @NotNull @Column(name = "gst_rate", precision = 5, scale = 2)
    private BigDecimal gstRate;

    @NotNull @Column(name = "cgst_amount", precision = 10, scale = 2)
    private BigDecimal cgstAmount;

    @NotNull @Column(name = "sgst_amount", precision = 10, scale = 2)
    private BigDecimal sgstAmount;

    @NotNull @Column(name = "total_price", precision = 10, scale = 2)
    private BigDecimal totalPrice;
}