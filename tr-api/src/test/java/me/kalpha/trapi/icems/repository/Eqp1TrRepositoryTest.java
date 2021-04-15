package me.kalpha.trapi.icems.repository;

import me.kalpha.trapi.icems.entity.Eqp1Tr;
import me.kalpha.trapi.icems.entity.Eqp1TrDet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class Eqp1TrRepositoryTest {
    @Autowired
    Eqp1TrRepository trRepository;
    @Autowired
    Eqp1TrDetRepository trDetRepository;

    @Test
    public void loadByName() {
        Eqp1Tr eqp1Tr = generateEqp1Tr("aaa1");
        trRepository.save(eqp1Tr);
        Optional<Eqp1Tr> optionalEqp1Tr = trRepository.findById(eqp1Tr.getId());
        assertTrue(optionalEqp1Tr.get().getId().equals(eqp1Tr.getId()));

        Eqp1TrDet eqp1TrDet = generateEqp1TrDet(eqp1Tr);
        trDetRepository.save(eqp1TrDet);
        Optional<Eqp1TrDet> optionalEqp1TrDet = trDetRepository.findById(eqp1TrDet.getId());
        assertTrue(optionalEqp1TrDet.get().getId().equals(eqp1TrDet.getId()));
    }

    private Eqp1Tr generateEqp1Tr(String name) {
        return Eqp1Tr.builder()
                .name(name).value(123454l).eventTime(LocalDateTime.now()).eqp1TrDets(new ArrayList<>())
                .build();
    }

    private Eqp1TrDet generateEqp1TrDet(Eqp1Tr eqp1Tr) {
        String col1 = "col1";
        Long col2 = 98765l;
        return new Eqp1TrDet(eqp1Tr, col1, col2);
    }
}