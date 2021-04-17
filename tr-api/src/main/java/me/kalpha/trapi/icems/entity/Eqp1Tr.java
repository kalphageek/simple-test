package me.kalpha.trapi.icems.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@Builder
@EqualsAndHashCode(of = {"id"})
@ToString
public class Eqp1Tr extends CreatedBaseEntity {
    @Id @GeneratedValue
    private Long id;

    private String name;
    private Long value;
    private LocalDateTime eventTime;

    public List<Eqp1TrDet> getEqp1TrDets() {
        if (eqp1TrDets == null)
            eqp1TrDets = new ArrayList<>();
        return eqp1TrDets;
    }

    @OneToMany(mappedBy = "eqp1Tr")
    List<Eqp1TrDet> eqp1TrDets;
}
