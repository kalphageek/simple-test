package me.kalpha.jpatest.tr.repository;

import me.kalpha.jpatest.tr.entity.Member;

import java.util.List;

public interface MemberCustomRepository {
    public List<Member> findByCustom();
}
