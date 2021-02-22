package me.kalpha.multiplication.repository;

import me.kalpha.multiplication.entity.MultiplicationSolvedEvent;
import org.springframework.data.repository.CrudRepository;

public interface MuliplicationRepository extends CrudRepository<MultiplicationSolvedEvent, Long> {
}
