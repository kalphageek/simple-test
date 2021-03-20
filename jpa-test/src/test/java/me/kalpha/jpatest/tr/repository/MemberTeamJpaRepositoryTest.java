package me.kalpha.jpatest.tr.repository;

import me.kalpha.jpatest.common.BaseControllerTest;
import me.kalpha.jpatest.config.Constants;
import me.kalpha.jpatest.tr.entity.Member;
import me.kalpha.jpatest.tr.entity.Team;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Rollback(value = false)
public class MemberTeamJpaRepositoryTest extends BaseControllerTest {
    @PersistenceContext(unitName = Constants.TR_UNIT_NAME)
    EntityManager em;

    @Test
    public void saveMembers() {
        Team team1 = new Team("TeamA");
        Team team2 = new Team("TeamB");

        em.persist(team1);
        em.persist(team2);

        Member member1 = new Member("member1", 10, team1);
        Member member2 = new Member("member2", 10, team1);
        Member member3 = new Member("member3", 10, team2);
        Member member4 = new Member("member4", 10, team2);

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        List<Member> memberList = em.createQuery("select m from Member m", Member.class)
                .getResultList();

        for (Member member : memberList) {
            System.out.println("member = " + member);
            System.out.println("-> member.team" + member.getTeam());
        }
    }
}
