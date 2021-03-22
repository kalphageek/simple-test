package me.kalpha.jpatest.tr.repository;

import me.kalpha.jpatest.tr.entity.Member;
import me.kalpha.jpatest.tr.entity.MemberDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
//    @Query(name = "Member.findByTeamId") //없어도 됨, NamedQuery에 있는지 메소드명으로 먼저 검색
    List<Member> findByTeamId(@Param("teamId") Long teamId);

    @Query(value = "select m from Member m where m.team.name = :teamName")
    List<Member> findByTeamName(@Param("teamName") String teamName);

    @Query(value = "select m.username from Member m where m.username like :value")
    List<String> findByUsernameLike(@Param("value") String value);

    @Query("select new me.kalpha.jpatest.tr.entity.MemberDto(m.id, m.username, t.name) from Member m join m.team t")
    List<MemberDto> findMemberDto();

    @Query("select m from Member m where m.username in :usernames")
    List<Member> findByUsernames(@Param("usernames") List<String> usernames);

    /**
     * 성능개선을 위해 Count Query를 임의로 지정할 수 있다.
     * @param id
     * @param pageable
     * @return
     */
    @Query(countQuery = "select count(m.id) from Member m where m.id > :id")
    Page<Member> findByIdGreaterThan(@Param("id") Long id, Pageable pageable);

    Slice<Member> findSliceByIdGreaterThan(Long id, Pageable pageable);

    List<Member> findListByIdGreaterThan(Long id, Pageable pageable);
}
