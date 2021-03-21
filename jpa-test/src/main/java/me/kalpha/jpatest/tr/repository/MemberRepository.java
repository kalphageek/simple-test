package me.kalpha.jpatest.tr.repository;

import me.kalpha.jpatest.tr.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query(name = "Member.findByTeamId")
    List<Member> findByTeamId(@Param("teamId") Long teamId);
}
