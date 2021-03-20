package me.kalpha.jpatest.tr.repository;

import me.kalpha.jpatest.tr.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
