package me.kalpha.multidatasource.batch;

import lombok.*;

import javax.persistence.*;

@Getter
@AllArgsConstructor @NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "userId")
@Entity
@Table(name = "account")
public class User1 {
    @Id
    @Column(name = "user_id")
    private String userId;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole1 role;
}
