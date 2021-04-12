package me.kalpha.trapi.icems.entity;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@EqualsAndHashCode(of = {"id"})
public class Eqp1Tr extends CreatedBaseEntity {
    @Id @Generated
    private Long id;

    private String name;
    private Long value;
    private LocalDateTime eventTime;

    @OneToMany(mappedBy = "eqp1Tr")
    List<Eqp1TrDet> eqp1TrDets = new ArrayList();
}
