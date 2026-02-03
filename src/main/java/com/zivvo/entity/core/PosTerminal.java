package com.zivvo.entity.core;

import com.zivvo.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "pos_terminals")
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class PosTerminal extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long storeId;

    @NotNull @Column(name = "terminal_code", length = 20)
    private String terminalCode;

    @Column(length = 50)
    private String name;

    @Column(name = "device_id", unique = true, length = 100)
    private String deviceId;

    @Column(name = "kiosk_type", length = 20)
    private String kioskType;

    @Column(name = "is_active")
    private boolean isActive = true;
}