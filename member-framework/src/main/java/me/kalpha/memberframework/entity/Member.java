package me.kalpha.memberframework.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_member")
@Getter @Setter
@NoArgsConstructor
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NonNull
    private String name;
    @NonNull
    private String account;
    @NonNull
    private String password;
    @Column(name = "last_access_dt")
    private LocalDateTime lastAccessDt;
    @Column(name = "reg_dt")
    private LocalDateTime regDt;

    public Member(Integer id, String name, String account, String password) {
        this.id = id;
        this.name = name;
        this.account = account;
        this.password = password;
    }
}
