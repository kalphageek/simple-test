package me.kalpha.trconsumerservice.trmart.repository;

import lombok.NonNull;
import me.kalpha.trconsumerservice.trmart.entity.Eqp1Tr;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Eqp1TrRepository extends JpaRepository<Eqp1Tr, Long> {
    public Optional<Eqp1Tr> findByName(@NonNull String name);
}
