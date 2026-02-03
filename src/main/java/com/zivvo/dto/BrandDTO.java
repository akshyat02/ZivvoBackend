package com.zivvo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class BrandDTO {
    @NotNull @Size(min = 2, max = 20)
    private String code;

    @NotNull @Size(min = 2, max = 100)
    private String name;

    private String businessType;
    private String currencyCode = "INR";
    private BigDecimal defaultGstRate = BigDecimal.valueOf(18.00);
}
