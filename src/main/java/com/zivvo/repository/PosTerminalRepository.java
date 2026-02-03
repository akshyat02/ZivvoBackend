package com.zivvo.repository;

import com.zivvo.entity.core.PosTerminal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PosTerminalRepository extends JpaRepository<PosTerminal, Long> {

    // Find all POS terminals for a store
    List<PosTerminal> findByStoreId(Long storeId);

    // Find active POS terminals for a store
    List<PosTerminal> findByStoreIdAndIsActiveTrue(Long storeId);

    // Find by device ID (unique)
    Optional<PosTerminal> findByDeviceId(String deviceId);

    // Find by terminal code
    Optional<PosTerminal> findByTerminalCode(String terminalCode);
}
