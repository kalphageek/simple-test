package me.kalpha.memberframework.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter @Setter
public class MemberDto {

    private Integer id;
    private String name;
    private String account;
    private String password;
    private LocalDateTime lastAccessDt;
    private LocalDateTime regDt;

    public Member toEntity() {
        return new Member(id, name, account, password);
    }
}
