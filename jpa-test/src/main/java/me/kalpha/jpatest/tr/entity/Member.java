package me.kalpha.jpatest.tr.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id","username","age"})
@NamedQuery(
        name = "Member.findByTeamId",
        query = "select m from Member m where m.team.id = :teamId"
)
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private int age;

    // FetchType.LAZY가 없으면 불필요한 Join발생 가능
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }

    public Member(String username, int age, Team team) {
        this.username = username;
        this.age = age;
        this.team = team;

        changeTeam(team);
    }

    public Member(String username) {
        this.username = username;
    }

    /**
     * 팀을 변경한다
     * 팀 변경시 Team객체가 가지고 있는 MemberSet의 구성에도 추가한다. (자동으로 안바뀐다)
     * @param team
     */
    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}

