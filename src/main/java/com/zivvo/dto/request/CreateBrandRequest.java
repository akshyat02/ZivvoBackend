package com.zivvo.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBrandRequest {
    @NotBlank @Size(max = 20)
    private String code;

    @NotBlank @Size(max = 100)
    private String name;

    @Size(max = 50)
    private String businessType;

    @Size(max = 30)
    private String industry;
}
