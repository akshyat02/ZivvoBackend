package com.zivvo.entity.product;

import com.zivvo.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import com.zivvo.entity.enums.UnitType;
import java.util.List;

@Entity
@Table(name = "products")
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class Product extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long brandId;

    @NotNull @Column(length = 200)
    private String name;

    @Column(columnDefinition = "text")
    private String description;

    @Column(length = 100)
    private String category;

    @NotNull @Column(name = "thumbnail_url", length = 500)
    private String thumbnailUrl;

    @Column(name = "image_urls", columnDefinition = "text[]")
    private List<String> imageUrls;

    @Column(columnDefinition = "text[]")
    private List<String> tags;

    @NotNull @Enumerated(EnumType.STRING)
    private UnitType unitType;
}