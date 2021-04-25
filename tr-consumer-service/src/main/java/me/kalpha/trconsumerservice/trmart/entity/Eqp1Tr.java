package me.kalpha.trconsumerservice.trmart.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
@Entity
@NoArgsConstructor
@Getter @Setter
public class Eqp1Tr implements Persistable<Long> {
    @Id
    private Long id;

    private String name;
    private Long value;
    private LocalDateTime eventTime;
    @CreatedDate
    private LocalDateTime createdDate;
    private String createdBy;

    public List<Eqp1TrDet> getEqp1TrDets() {
        if (eqp1TrDets == null)
            eqp1TrDets = new ArrayList<>();
        return eqp1TrDets;
    }

    @OneToMany(mappedBy = "eqp1Tr")
    List<Eqp1TrDet> eqp1TrDets;

    @Override
    public boolean isNew() {
        return createdDate == null;
    }
}
