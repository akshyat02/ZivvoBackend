package com.zivvo.service;

import com.zivvo.entity.core.Brand;
import com.zivvo.entity.core.PosTerminal;
import com.zivvo.entity.core.Store;
import com.zivvo.entity.enums.Role;
import com.zivvo.entity.management.Admin;
import com.zivvo.repository.AdminRepository;
import com.zivvo.repository.BrandRepository;
import com.zivvo.repository.PosTerminalRepository;
import com.zivvo.repository.StoreRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Transactional
@Slf4j
public class Seeder {
    @Autowired
    private AdminRepository adminRepo;
    @Autowired private BrandRepository brandRepo;
    @Autowired private StoreRepository storeRepo;
    @Autowired private PosTerminalRepository posRepo;

    @EventListener(ApplicationReadyEvent.class)
    @Async
    public void seed() {
        if (adminRepo.count() == 0) {
            log.info("🌱 Starting initial data seeding...");

            // 1. SUPERADMIN
            Admin superAdmin = Admin.builder()
                    .email("super@zivvo.com")
                    .name("Super Admin")
                    .role(Role.SUPERADMIN)
                    .isActive(true)
                    .build();
            Admin savedSuper = adminRepo.save(superAdmin);
            log.info("SUPERADMIN: {} (ID: {})", savedSuper.getEmail(), savedSuper.getId());

            // 2. NIKE BRAND
            Brand nike = Brand.builder()
                    .code("NIKE")
                    .name("Nike India Pvt Ltd")
                    .businessType("RETAIL")
                    .currencyCode("INR")
                    .defaultGstRate(BigDecimal.valueOf(18.00))
                    .build();
            Brand savedNike = brandRepo.save(nike);
            log.info("NIKE BRAND ID: {}", savedNike.getId());

            // 3. NIKE BRAND ADMIN
            Admin brandAdmin = Admin.builder()
                    .brandId(savedNike.getId())
                    .email("nike.admin@zivvo.com")
                    .name("Nike Brand Admin")
                    .role(Role.BRAND_ADMIN)
                    .isActive(true)
                    .build();
            adminRepo.save(brandAdmin);

            // 4. MG ROAD STORE
            Store mgRoad = Store.builder()
                    .brandId(savedNike.getId())
                    .code("BLR001")
                    .name("Nike MG Road Flagship")
                    .address("MG Road, Bengaluru, Karnataka 560001")
                    .latitude(BigDecimal.valueOf(12.9753))
                    .longitude(BigDecimal.valueOf(77.5999))
                    .isActive(true)
                    .build();
            Store savedStore = storeRepo.save(mgRoad);
            log.info("MG ROAD STORE ID: {}", savedStore.getId());

            // 5. STORE MANAGER
            Admin storeManager = Admin.builder()
                    .brandId(savedNike.getId())
                    .storeId(savedStore.getId())
                    .email("mgr.manager@zivvo.com")
                    .name("MG Road Store Manager")
                    .role(Role.STORE_MANAGER)
                    .isActive(true)
                    .build();
            adminRepo.save(storeManager);

            // 6. POS TERMINAL
            PosTerminal pos1 = PosTerminal.builder()
                    .storeId(savedStore.getId())
                    .terminalCode("POS001")
                    .name("MG Road Counter 1")
                    .deviceId("POS-MG001-ABC123")
                    .kioskType("COUNTER")
                    .isActive(true)
                    .build();
            posRepo.save(pos1);
            log.info("COMPLETE HIERARCHY SEEDED! Ready for POS billing.");
        }
     else {
        log.info("Data already seeded. Skipping...");
        log.info("Current hierarchy:");
        log.info("   - Super Admins: {}", adminRepo.findAll().stream()
                .filter(a -> a.getRole() == Role.SUPERADMIN).count());
        log.info("   - Total Brands: {}", brandRepo.count());
        log.info("   - Total Stores: {}", storeRepo.count());
        log.info("   - Total POS Terminals: {}", posRepo.count());
    }

}
}