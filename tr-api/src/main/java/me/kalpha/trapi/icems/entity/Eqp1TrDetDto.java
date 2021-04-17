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
    private Eqp1TrDto eqp1TrDto;
}
