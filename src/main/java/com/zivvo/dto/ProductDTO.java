package com.zivvo.dto;

import com.zivvo.entity.enums.UnitType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.util.List;

@Data
public class ProductDTO {
    @NotNull @Size(min = 2, max = 200)
    private String name;

    private String description;
    private String category;
    @NotNull
    private String thumbnailUrl;
    private List<String> imageUrls;
    private List<String> tags;
    @NotNull
    private UnitType unitType;
}
