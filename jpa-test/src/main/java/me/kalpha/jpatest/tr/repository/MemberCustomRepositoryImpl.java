package me.kalpha.jpatest.tr.repository;

import me.kalpha.jpatest.config.Constants;
import me.kalpha.jpatest.tr.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class MemberCustomRepositoryImpl implements MemberCustomRepository {
    @PersistenceContext(unitName = Constants.TR_UNIT_NAME)
    EntityManager em;

    @Override
    public List<Member> findByCustom() {
        List results = em.createQuery("select m from Member m")
                .getResultList();
        return results;
    }
}
