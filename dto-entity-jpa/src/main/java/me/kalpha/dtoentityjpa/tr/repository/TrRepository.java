package me.kalpha.dtoentityjpa.tr.repository;

import me.kalpha.dtoentityjpa.tr.entity.TrEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrRepository extends JpaRepository<TrEntity, Long> {
}
