package me.kalpha.dtoentityjpa.tr.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @{link TrMaterialCodeEntity}와 양방향 관계
 */
@Entity
@Table
public class TrEntity implements Serializable {
    @Id @GeneratedValue
    private long id;
    private String name;
    private int count;

    @OneToMany(mappedBy = "tr", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TrMaterialCodeEntity> trCodeSet = new HashSet<>();

    public void addTrCode(final TrMaterialCodeEntity trCode) {
        trCode.setTr(this);
        trCodeSet.add(trCode);
    }
}
