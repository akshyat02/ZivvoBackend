package com.zivvo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BrandResponse {
    private Long id;
    private String code;
    private String name;
    private String businessType;
    private String industry;
    private String currencyCode;
    private BigDecimal defaultGstRate;
    private LocalDateTime createdAt;
}