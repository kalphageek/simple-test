package me.kalpha.dtoentityjpa.tr.service;

import me.kalpha.dtoentityjpa.tr.dto.TrDto;
import me.kalpha.dtoentityjpa.tr.entity.TrEntity;
import me.kalpha.dtoentityjpa.tr.entity.TrMaterialCodeEntity;
import me.kalpha.dtoentityjpa.tr.repository.TrRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TrServiceTest {
    @Autowired
    TrService trService;
    @Autowired
    ModelMapper modelMapper;

    @Test
    public void saveTr() {
        Set<TrDto.MaterialCode> set = new HashSet<>();

        TrDto.MaterialCode code01 = TrDto.MaterialCode.builder()
                .dut("dut01")
                .unitId("unit01")
                .build();
        TrDto.MaterialCode code02 = TrDto.MaterialCode.builder()
                .dut("dut01")
                .unitId("unit01")
                .build();
        set.add(code01);
        set.add(code02);

        TrDto trDto = TrDto.builder()
                .name("jjd")
                .count(10)
                .codeSet(set)
                .build();

        trService.save(trDto);
    }
}