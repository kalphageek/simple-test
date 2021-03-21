package me.kalpha.jpatest.tr.repository;

import me.kalpha.jpatest.config.Constants;
import me.kalpha.jpatest.tr.entity.Member;
import me.kalpha.jpatest.tr.entity.MemberDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
//    @Query(name = "Member.findByTeamId") //없어도 됨, NamedQuery에 있는지 메소드명으로 먼저 검색
    List<Member> findByTeamId(@Param("teamId") Long teamId);

    @Query(value = "select m from Member m where m.team.name = :teamName")
    List<Member> findByTeamName(@Param("teamName") String teamName);

    @Query(value = "select m.username from Member m where m.username like :value")
    List<String> findByUsernameLike(@Param("value") String value);

    @Query("select new me.kalpha.jpatest.tr.entity.MemberDto(m.id, m.username, t.name) from Member m join m.team t")
    List<MemberDto> findMemberDto();
}
