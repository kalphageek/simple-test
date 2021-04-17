package me.kalpha.trapi.icems.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Eqp1TrDet extends CreatedBaseEntity {
    @Id @GeneratedValue
    private Long id;
    private String col1;
    private Long col2;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tr_id")
    private Eqp1Tr eqp1Tr;

    public void assignEqp1Tr(Eqp1Tr eqp1Tr) {
        this.eqp1Tr = eqp1Tr;
        this.eqp1Tr.getEqp1TrDets().add(this);
    }
}
