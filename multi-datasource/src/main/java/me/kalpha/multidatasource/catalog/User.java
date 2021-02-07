package me.kalpha.multidatasource.catalog;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@AllArgsConstructor @NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "userId")
@Entity
@Table(name = "account")
public class User {
    @Id
    @Column(name = "user_id")
    private String userId;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole role;
}
