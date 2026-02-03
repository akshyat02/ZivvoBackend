package com.zivvo.entity.core;

import com.zivvo.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;

@Entity
@Table(name = "brands")
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class Brand extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true, length = 20)
    private String code;

    @NotNull @Column(length = 100)
    private String name;

    private String businessType;
    private String industry;

    @Column(length = 3)
    private String currencyCode = "INR";

    private BigDecimal defaultGstRate = BigDecimal.valueOf(18.00);

    @Column(length = 15)
    private String contactPhone;

    @Column(length = 100)
    private String contactEmail;

//    @Column(columnDefinition = "jsonb DEFAULT '{}'")
//    private String config;
}
