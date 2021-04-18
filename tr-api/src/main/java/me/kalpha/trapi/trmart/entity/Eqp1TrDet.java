package me.kalpha.trapi.trmart.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.kalpha.trapi.common.CreatedBaseEntity;

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
