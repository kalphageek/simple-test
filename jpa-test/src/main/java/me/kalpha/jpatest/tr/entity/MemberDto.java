package me.kalpha.jpatest.tr.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberDto {
    private Long id;
    private String username;
    private String teamName;

    public MemberDto(Member member) {
        id = member.getId();
        username = member.getUsername();
        teamName = member.getTeam()==null?null:member.getTeam().getName();
    }
}
