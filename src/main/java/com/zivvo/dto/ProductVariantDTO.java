package com.zivvo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProductVariantDTO {
    @NotNull @Size(max = 50)
    private String sku;

    @Size(max = 50)
    private String barcode;
    private String size;
    private String color;
    @NotNull
    private BigDecimal mrp;
    @NotNull
    private BigDecimal basePrice;
    @NotNull
    private BigDecimal gstRate;

    private BigDecimal cgstRate;
    private BigDecimal sgstRate;
}
