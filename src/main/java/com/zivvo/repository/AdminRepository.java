package com.zivvo.repository;

import com.zivvo.entity.management.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByEmail(String email);
    List<Admin> findByBrandId(Long brandId);
    List<Admin> findByStoreId(Long storeId);
}
