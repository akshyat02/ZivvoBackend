package com.zivvo.entity.management;

import com.zivvo.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import com.zivvo.entity.enums.Role;

@Entity
@Table(name = "admins")
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class Admin extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long brandId;  // NULL for SUPERADMIN

    private Long storeId;  // NULL for SUPERADMIN/BRAND_ADMIN

    @NotNull @Column(unique = true, length = 100)
    private String email;

    @Column(length = 100)
    private String name;

    @NotNull @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "password_hash", length = 255)
    private String passwordHash;

    @Column(name = "is_active")
    private boolean isActive = true;
}