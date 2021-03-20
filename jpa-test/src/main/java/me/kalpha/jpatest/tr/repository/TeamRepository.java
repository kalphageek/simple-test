package me.kalpha.jpatest.tr.repository;

import me.kalpha.jpatest.tr.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
