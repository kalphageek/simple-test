package me.kalpha.dtoentityjpa.tr.repository;

import me.kalpha.dtoentityjpa.tr.entity.TrMaterialCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrMaterialCodeRepository extends JpaRepository<TrMaterialCodeEntity, TrMaterialCodeEntity.Pk> {
}
