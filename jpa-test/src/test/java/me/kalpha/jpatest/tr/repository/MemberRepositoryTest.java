package me.kalpha.jpatest.tr.repository;

import me.kalpha.jpatest.common.BaseControllerTest;
import me.kalpha.jpatest.tr.entity.Member;
import me.kalpha.jpatest.tr.entity.MemberDto;
import me.kalpha.jpatest.tr.entity.Team;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional
@Rollback
public class MemberRepositoryTest extends BaseControllerTest {
    //모두 같은 영속성 Context를 사용한다
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    TeamRepository teamRepository;

    @Test
    public void auditingTest() throws Exception {
        Member member = memberRepository.save(new Member("member1", 99));
        Thread.sleep(100);
        member.setUsername("member3");

        System.out.println("member = " + member);
    }

    @Test
    public void findCustomRepository() {
        List<Member> byCustom = memberRepository.findByCustom();
        System.out.println("byCustom.size() = " + byCustom.size());
    }

    @Test
    public void findReadOnlyByUsername() {
        List<Member> memberList = memberRepository.findReadOnlyByUsername("memberB");
        Member member = memberList.get(0);
        System.out.println("member = " +member);

        member.setUsername("memberB");
    }

    @Test
    public void findFetchJoin() {
        List<Member> memberList = memberRepository.findFetchJoin(165);
        for (Member member : memberList) {
            System.out.println("member.getUsername() = " + member.getUsername());
//            System.out.println("member.getTeam().getName() = " + member.getTeam()==null?null:member.getTeam().getName());
        }
    }

    @Test
    public void bulkAgePlus() {
//        memberRepository.save(new Member("member1", 120));
//        memberRepository.save(new Member("member2", 110));
//        memberRepository.save(new Member("member3", 122));
//        memberRepository.save(new Member("member4", 165));
//        memberRepository.save(new Member("member5", 160));

        int updateCount = memberRepository.bulkAgePlus(100);
        System.out.println("updateCount = " + updateCount);

        assertTrue(updateCount == 15);
    }

    @Test
    public void findListByIdGreaterThan() {
        Long id = 10L;
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "id"));
        List<Member> list = memberRepository.findListByIdGreaterThan(id, pageRequest);
        list.stream().forEach(System.out::println);
    }

    @Test
    public void findSliceByIdGreaterThan() {
        Long id = 10L;
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "id"));
        Slice<Member> page = memberRepository.findSliceByIdGreaterThan(id, pageRequest);
        List<Member> cotent = page.getContent();
        cotent.stream().forEach(System.out::println);

        assertTrue(cotent.size() == 3);
//        assertTrue(page.getTotalElements() > 5);
        assertTrue(page.getNumber() == 0);
//        assertTrue(page.getTotalPages() >= 2);
        assertTrue(page.isFirst() == true);
        assertTrue(page.hasNext() == true);
    }

    /**
     * Page를 Dto로 변환하기
     */
    @Test
    public void findDtoByIdGreaterThan() {
        Long id = 10L;
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "id"));
        Page<Member> page = memberRepository.findByIdGreaterThan(id, pageRequest);
        Page<MemberDto> toMap = page.map(m -> new MemberDto(m.getId(), m.getUsername(), m.getTeam()==null?null:m.getTeam().getName()));

        List<MemberDto> cotent = toMap.getContent();
        cotent.stream().forEach(System.out::println);
    }

    @Test
    public void findByIdGreaterThan() {
        Long id = 10L;
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "id"));
        Page<Member> page = memberRepository.findByIdGreaterThan(id, pageRequest);
        List<Member> cotent = page.getContent();
        cotent.stream().forEach(System.out::println);

        assertTrue(cotent.size() == 3);
        assertTrue(page.getTotalElements() > 5);
        assertTrue(page.getNumber() == 0);
        assertTrue(page.getTotalPages() >= 2);
        assertTrue(page.isFirst() == true);
        assertTrue(page.hasNext() == true);
    }

    @Test
    public void findByUsernames() {
        List list = new ArrayList();
        list.add("memberB");
        list.add("memberC");
        List<Member> memberList = memberRepository.findByUsernames(list);

        memberList.stream().forEach(System.out::println);
        assertTrue(memberList.size() >= 2);
    }

    @Test
    public void findMemberDto() {
        List<MemberDto> memberDtoList = memberRepository.findMemberDto();
        memberDtoList.stream().forEach(System.out::println);

        assertTrue(memberDtoList.size() > 0);
    }

    @Test
    public void findByUsernameLike() {
        List<String> memberList = memberRepository.findByUsernameLike("%B");
        memberList.stream().forEach(System.out::println);

        assertTrue(memberList.size() > 0);
    }

    @Test
    public void findByTeamName() {
// 에러발생 원인 못찾음
//        Optional<Team> optionalTeam = teamRepository.findById(84L);
//        Team team = optionalTeam.get();
//
//        Member member = new Member("memberD", 12, team);
//        memberRepository.save(member);

        List<Member> memberList = memberRepository.findByTeamName("TeamB");
        assertTrue(memberList.size() > 0);
    }

    @Test
    public void findByTeamId() {
        Team team = new Team("TeamC");
        teamRepository.save(team);

        Member member = new Member("memberC", 10, team);
        memberRepository.save(member);

        List<Member> memberList = memberRepository.findByTeamId(team.getId());
        assertTrue(memberList.size() > 0);
    }

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

    private void generateDate() {
        Team team1 = new Team("Team1");
        Team team2 = new Team("Team2");
        teamRepository.save(team1);
        teamRepository.save(team2);

        Member member1 = new Member("member1", 10, team1);
        Member member2 = new Member("member2", 11, team1);
        memberRepository.save(member1);
        memberRepository.save(member2);

        Member member3 = new Member("member3", 20, team2);
        Member member4 = new Member("member4", 21, team2);
        memberRepository.save(member3);
        memberRepository.save(member4);
    }
}
