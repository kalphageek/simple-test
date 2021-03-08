package me.kalpha.dtoentityjpa.tr.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
@Builder
public class TrDto {
    private String name;
    private int count;
    private Set<MaterialCode> codeSet;

    @Getter
    @AllArgsConstructor
    @Builder
    public static class MaterialCode {
        private String unitId;
        private String dut;
    }
}
