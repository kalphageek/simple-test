package me.kalpha.trapi.icems.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Eqp1TrDet extends CreatedBaseEntity {
    @Id @GeneratedValue
    private Long id;
    private String col1;
    private Long col2;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tr_id")
    private Eqp1Tr eqp1Tr;

    @Builder
    public Eqp1TrDet(Eqp1Tr eqp1Tr, String col1, Long col2) {
        this.eqp1Tr = eqp1Tr;
        this.col1 = col1;
        this.col2 = col2;
        assignEqp1Tr(eqp1Tr);
    }

    public void assignEqp1Tr(Eqp1Tr eqp1Tr) {
        this.eqp1Tr = eqp1Tr;
        this.eqp1Tr.getEqp1TrDets().add(this);
    }
}
