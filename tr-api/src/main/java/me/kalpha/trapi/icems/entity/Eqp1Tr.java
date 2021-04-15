package me.kalpha.trapi.icems.entity;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
    @Id @GeneratedValue
    private Long id;

    private String name;
    private Long value;
    private LocalDateTime eventTime;

    @OneToMany(mappedBy = "eqp1Tr")
    List<Eqp1TrDet> eqp1TrDets;

    public Eqp1Tr(String name, Long value, LocalDateTime eventTime) {
        this.name = name;
        this.value = value;
        this.eventTime = eventTime;
    }

    public void assignEqp1TrDet(Eqp1TrDet eqp1TrDet) {
        if (eqp1TrDets == null) this.eqp1TrDets =  new ArrayList<>();
        this.eqp1TrDets.add(eqp1TrDet);
    }
}
