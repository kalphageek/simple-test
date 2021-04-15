package me.kalpha.trapi.icems.service;

import me.kalpha.trapi.icems.entity.Eqp1Tr;
import me.kalpha.trapi.icems.entity.Eqp1TrDet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class Eqp1TrServiceTest {
    @Autowired
    Eqp1TrService eqp1TrService;

    @Test
    public void findByName() {
        Eqp1Tr eqp1Tr = generateEqp1Tr("lot1");
        Eqp1Tr savedEqp1Tr = eqp1TrService.saveTr(eqp1Tr);

        assertTrue(savedEqp1Tr.getId().equals(eqp1Tr.getId()));
        assertTrue(savedEqp1Tr.getEqp1TrDets().stream().count() == 1);
    }

    private Eqp1Tr generateEqp1Tr(String name) {
        Eqp1TrDet eqp1TrDet = new Eqp1TrDet("col1", 98765l);

        Eqp1Tr eqp1Tr = Eqp1Tr.builder()
                .name(name).value(123454l).eventTime(LocalDateTime.now())
                .build();
        eqp1Tr.assignEqp1TrDet(eqp1TrDet);

        return eqp1Tr;
    }
}