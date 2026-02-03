package com.zivvo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnalyticsResponse {
    private Long totalBrands;
    private Long totalStores;
    private Long totalOrders;
    private BigDecimal totalRevenue;
    private Long activeStores;
}

