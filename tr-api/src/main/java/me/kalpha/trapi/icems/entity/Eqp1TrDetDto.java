package me.kalpha.trapi.icems.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class Eqp1TrDetDto {
    private String col1;
    private Long col2;

    public Eqp1TrDet toEntity(Eqp1Tr eqp1Tr) {
        return new Eqp1TrDet(eqp1Tr, col1, col2);
    }
}
