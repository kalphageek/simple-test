package me.kalpha.memberframework.repository;

import me.kalpha.memberframework.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findByAccount(String account);
}
