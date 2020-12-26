package me.kalpha.memberframework.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDateTime;

/**
 * Web UI에서 사용자 입력정보를 받아서 Repository에 Member객체로 전달하기위한 DTO
 */
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
