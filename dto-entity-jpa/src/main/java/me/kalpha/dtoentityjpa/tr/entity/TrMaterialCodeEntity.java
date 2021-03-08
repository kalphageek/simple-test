package me.kalpha.dtoentityjpa.tr.entity;

import lombok.EqualsAndHashCode;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @{link TrMEntity}와 양방향 관계
 */
@Setter
@Entity
@Table
@EqualsAndHashCode(of = "pk")
public class TrMaterialCodeEntity implements Serializable {
    @EmbeddedId
    private Pk pk;

    //양방향
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tr_id")
    private TrEntity tr;
    private String dut;

    @Embeddable
    public static class Pk implements Serializable {
        @Column(name = "tr_id")
        private long trId;
        @Column(name = "unit_id")
        private String unitId;

        @Override
        public String toString() {
            return String.format("%d.%s", trId, unitId);
        }
    }
}
