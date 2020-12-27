package me.kalpha.memberframework.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_member")
@Getter @Setter
@NoArgsConstructor
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NonNull
    private String name;
    @Column(unique = true)
    private String account;
    @NonNull
    private String password;
    private Role role;
    @Column(name = "last_access_dt")
    private LocalDateTime lastAccessDt;
    @Column(name = "reg_dt")
    private LocalDateTime regDt;

    public Member(Integer id, String name, String account, String password, Role role) {
        this.id = id;
        this.name = name;
        this.account = account;
        this.password = password;
        this.role = role;
    }

    public enum Role{
        ROLE_MEMBER("일반사용자"),
        ROLE_ADMIN("관리자");

        private String text;
        Role (String text) {
            this.text = text;
        }
    }
}
