package me.kalpha.jpatest.tr.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberDto {
    private Long id;
    private String username;
    private String teamName;
}
