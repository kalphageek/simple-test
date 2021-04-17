package me.kalpha.trapi.icems.service;

import me.kalpha.trapi.common.BaseControllerTest;
import me.kalpha.trapi.icems.entity.Eqp1Tr;
import me.kalpha.trapi.icems.entity.Eqp1TrDet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class Eqp1TrServiceTest {
    @Autowired
    Eqp1TrService eqp1TrService;

    @Test
    public void findByName() {
        String trName = "lot1";
        Eqp1Tr eqp1Tr = Eqp1Tr.builder()
                .name(trName).value(123454l).eventTime(LocalDateTime.now())
                .build();
        Eqp1TrDet eqp1TrDet1 = new Eqp1TrDet(eqp1Tr, "col1", 98765l);
        Eqp1TrDet eqp1TrDet2 = new Eqp1TrDet(eqp1Tr, "col2", 55l);

        Eqp1Tr savedEqp1Tr = eqp1TrService.saveTr(eqp1Tr);

        assertTrue(savedEqp1Tr.getId().equals(eqp1Tr.getId()));
        assertTrue(savedEqp1Tr.getEqp1TrDets().stream().count() == 2);
    }
}