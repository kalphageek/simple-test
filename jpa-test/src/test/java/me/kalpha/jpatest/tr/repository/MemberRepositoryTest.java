package me.kalpha.jpatest.tr.repository;

import me.kalpha.jpatest.common.BaseControllerTest;
import me.kalpha.jpatest.tr.entity.Member;
import me.kalpha.jpatest.tr.entity.Team;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MemberRepositoryTest extends BaseControllerTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    TeamRepository teamRepository;

    @Test
    public void testMember() {
        Team team = new Team("TeamB");
        teamRepository.save(team);

        Member member = new Member("memberB", 10, team);
        Member member1 = memberRepository.save(member);

        assertTrue(member1.getId().equals(member.getId()));
        assertTrue(member1.getUsername().equals(member.getUsername()));

        // PersistenceContext안에서는 전달된 개체와 저장된 개체가 같음을 보장한다
        assertTrue(member1 == member);
    }


    @Test
    public void crudTest() {
        Member member1 = new Member("aaa");
        Member member2 = new Member("bbb");
        memberRepository.save(member1);
        memberRepository.save(member2);

        Member m1 = memberRepository.findById(member1.getId()).get();
        Member m2 = memberRepository.findById(member2.getId()).get();
        assertTrue(m1.getId() == member1.getId());
        assertTrue(m1.getId() == member1.getId());

        List<Member> all = memberRepository.findAll();
        assertTrue(all.size() >= 2);

        long count = memberRepository.count();
        assertTrue(count >= 2);

        memberRepository.delete(member1);
        memberRepository.delete(member2);

        long detetedCount = memberRepository.count();
        assertTrue(count-2 == detetedCount);
    }
}
