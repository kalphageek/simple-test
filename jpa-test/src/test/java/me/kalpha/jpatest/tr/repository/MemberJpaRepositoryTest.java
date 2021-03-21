package me.kalpha.jpatest.tr.repository;

import me.kalpha.jpatest.common.BaseControllerTest;
import me.kalpha.jpatest.tr.entity.Member;
import me.kalpha.jpatest.tr.entity.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 자동으로 PersistenceContext에서 rollback(sql을 보내지 않음) 한다.
 * @Rollback(value=false)하면 sql을 보내서 저장함
 * 동일한 Txn에서 PersistenceContext는 전달된 개체와 저장된 개체가 같음을 보장한다 *
 */
@Transactional
@Rollback(value = false)
class MemberJpaRepositoryTest extends BaseControllerTest {
    @Autowired
    MemberJpaRepository memberJpaRepository;
    @Autowired
    TeamJpaRepository teamJpaRepository;

    @Test
    public void testMember() {
        Team team = new Team("TeamA");
        teamJpaRepository.save(team);

        Member member = new Member("memberA", 10, team);
        Member member1 = memberJpaRepository.save(member);

        assertTrue(member1.getId().equals(member.getId()));
        assertTrue(member1.getUsername().equals(member.getUsername()));

        // PersistenceContext안에서는 전달된 개체와 저장된 개체가 같음을 보장한다
        assertTrue(member1 == member);
    }

    @Test
    public void crudTest() {
        Member member1 = new Member("aaa");
        Member member2 = new Member("bbb");
        memberJpaRepository.save(member1);
        memberJpaRepository.save(member2);

        Member m1 = memberJpaRepository.findById(member1.getId()).get();
        Member m2 = memberJpaRepository.findById(member2.getId()).get();
        assertTrue(m1.getId() == member1.getId());
        assertTrue(m1.getId() == member1.getId());

        List<Member> all = memberJpaRepository.findAll();
        assertTrue(all.size() >= 2);

        long count = memberJpaRepository.count();
        assertTrue(count >= 2);

        memberJpaRepository.delete(member1);
        memberJpaRepository.delete(member2);

        long detetedCount = memberJpaRepository.count();
        assertTrue(count-2 == detetedCount);
    }
}