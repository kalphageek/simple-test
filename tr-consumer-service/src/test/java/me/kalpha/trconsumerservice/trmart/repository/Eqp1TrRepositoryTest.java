package me.kalpha.trconsumerservice.trmart.repository;

import lombok.extern.slf4j.Slf4j;
import me.kalpha.trconsumerservice.trmart.entity.Eqp1Tr;
import me.kalpha.trconsumerservice.trmart.entity.Eqp1TrDet;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@ActiveProfiles("test")
@SpringBootTest
public class Eqp1TrRepositoryTest {
    @Autowired
    Eqp1TrRepository trRepository;
    @Autowired
    Eqp1TrDetRepository trDetRepository;
    @Autowired
    ModelMapper eqp1TrMapper;
    @Autowired
    ModelMapper eqp1TrDetMapper;

    @Test
    public void createTr() {
        eqp1TrMapper.addMappings(new PropertyMap<Eqp1Tr, Eqp1Tr>() {
            @Override
            protected void configure() {
                map().setCreatedDate(null);
            }
        });
        eqp1TrDetMapper.addMappings(new PropertyMap<Eqp1TrDet, Eqp1TrDet>() {
            @Override
            protected void configure() {
                map().setCreatedDate(null);
            }
        });

        String trName = "lot1";
        Eqp1Tr eqp1TrDto = generateEqp1Tr(trName);
        Eqp1Tr eqp1Tr = eqp1TrMapper.map(eqp1TrDto, Eqp1Tr.class);
        List<Eqp1TrDet> eqp1TrDets = eqp1TrDto.getEqp1TrDets().stream()
                .map(o -> eqp1TrDetMapper.map(o, Eqp1TrDet.class))
                .collect(Collectors.toList());

        // Save at Eqp1Tr, Eqp1TrDet
        trRepository.save(eqp1Tr);
        trDetRepository.saveAll(eqp1TrDets);

        assertTrue(eqp1Tr.getEqp1TrDets().stream().count() == 2);
    }

    private Eqp1Tr generateEqp1Tr(String trName) {
        Eqp1Tr eqp1Tr = new Eqp1Tr();
        eqp1Tr.setId(1l);
        eqp1Tr.setValue(1233l);
        eqp1Tr.setName(trName);
        eqp1Tr.setCreatedBy("2043738");
        eqp1Tr.setCreatedDate(LocalDateTime.now());
        eqp1Tr.setEventTime(LocalDateTime.now());;

        Eqp1TrDet eqp1TrDet = new Eqp1TrDet();
        eqp1TrDet.setId(2l);
        eqp1TrDet.setCol1("col1");
        eqp1TrDet.setCol2(234235l);
        eqp1TrDet.setCreatedBy("2043738");
        eqp1TrDet.setCreatedDate(LocalDateTime.now());
        eqp1TrDet.assignEqp1Tr(eqp1Tr);

        Eqp1TrDet eqp1TrDet2 = new Eqp1TrDet();
        eqp1TrDet2.setId(3l);
        eqp1TrDet2.setCol1("col1");
        eqp1TrDet2.setCol2(234235l);
        eqp1TrDet2.setCreatedBy("2043738");
        eqp1TrDet2.setCreatedDate(LocalDateTime.now());
        eqp1TrDet2.assignEqp1Tr(eqp1Tr);

        return eqp1Tr;
    }
}