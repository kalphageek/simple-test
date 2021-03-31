package me.kalpha.jpatest.tr.repository;

import me.kalpha.jpatest.tr.entity.Member;
import me.kalpha.jpatest.tr.entity.MemberDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.QueryHint;
import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberCustomRepository {
    // @Query(name = "Member.findByTeamId") //없어도 됨, NamedQuery에 있는지 메소드명으로 먼저 검색
    List<Member> findByTeamId(@Param("teamId") Long teamId);

    @Query(value = "select m from Member m where m.team.name = :teamName")
    List<Member> findByTeamName(@Param("teamName") String teamName);

    // like절 매핑, String value = "T%" 형태로 입력한다.
    @Query(value = "select m.username from Member m where m.username like :value")
    List<String> findByUsernameLike(@Param("value") String value);

    // Dto로 바로 매핑하기, full path를 입력해야 한다.
    @Query("select new me.kalpha.jpatest.tr.entity.MemberDto(m.id, m.username, t.name) from Member m join m.team t")
    List<MemberDto> findMemberDto();

    // in 절을 위해 Multi Value 넘기기
    @Query("select m from Member m where m.username in :usernames")
    List<Member> findByUsernames(@Param("usernames") List<String> usernames);

    // Paging Query인 경우 성능개선을 위해 Count Query를 임의로 지정할 수 있다.
    @Query(countQuery = "select count(m.id) from Member m where m.id > :id")
    Page<Member> findByIdGreaterThan(@Param("id") Long id, Pageable pageable);

    // Slice는 Page수량 + 1 개를 가져온다.
    Slice<Member> findSliceByIdGreaterThan(Long id, Pageable pageable);

    // find와 By 사이에는 아무거나 와도 상관없다.
    List<Member> findListByIdGreaterThan(Long id, Pageable pageable);

    @Transactional
    @Modifying(clearAutomatically = true) //executeUpdate 실행함. (flush를 자동으로)
    @Query("update Member m set m.age = m.age + 1 where m.age > :age")
    int bulkAgePlus(@Param("age") int age);

    /**
     * Fetch Join
     * fetch = FetchType.LAZY인 경우도 한번의 Query로 Member와 Team을 Join해서 데이터 Return 한다.
     * @return
     */
    @Query("select m from Member m left join fetch m.team where age >= :age")
    List<Member> findFetchJoin(@Param("age") int age);

    /**
     * fetch join
     * findFetchJoin 과 동일하다
     * @return
     */
    @EntityGraph(attributePaths = {"team"})
    @Query("select m from Member m where age >= :age")
    List<Member> findFetchJoin2(@Param("age") int age);

    @Override
    /**
     * JPA가 제공해주는  메소드에 Fetch Join을 적용하기 위해 JPQL을 작성할 필요가 없도록 해준다
     * team 개체에 대해 Fetch Join 해라.
     * @return
     */
    @EntityGraph(attributePaths = {"team"})
    List<Member> findAll();

    /**
     * JPA Hint를 사용한다. Query를 ReadOnly로 사용한다 : 성능상 이점이 있음, update 해도 반영 안됨
     * @param username
     * @return
     */
    @QueryHints(
            @QueryHint(name = "org.hibernate.readOnly", value = "true")
    )
    List<Member> findReadOnlyByUsername(String username);
}
