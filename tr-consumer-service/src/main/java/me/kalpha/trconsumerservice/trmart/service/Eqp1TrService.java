package me.kalpha.trconsumerservice.trmart.service;

import lombok.extern.slf4j.Slf4j;
import me.kalpha.trconsumerservice.trmart.entity.Eqp1Tr;
import me.kalpha.trconsumerservice.trmart.entity.Eqp1TrDet;
import me.kalpha.trconsumerservice.trmart.repository.Eqp1TrDetRepository;
import me.kalpha.trconsumerservice.trmart.repository.Eqp1TrRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class Eqp1TrService {
    @Autowired
    Eqp1TrRepository trRepository;
    @Autowired
    Eqp1TrDetRepository trDetRepository;
    @Autowired
    ModelMapper eqp1TrMapper;
    @Autowired
    ModelMapper eqp1TrDetMapper;

    @Transactional
    public Eqp1Tr createTr(Eqp1Tr receivedEqp1Tr) {
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

        Eqp1Tr eqp1Tr = eqp1TrMapper.map(receivedEqp1Tr, Eqp1Tr.class);
        List<Eqp1TrDet> eqp1TrDets = receivedEqp1Tr.getEqp1TrDets().stream()
                .map(o -> eqp1TrDetMapper.map(o, Eqp1TrDet.class))
                .collect(Collectors.toList());

        // Save at Eqp1Tr, Eqp1TrDet
        // CascadeType이 지정되어 있지 않기 때문에 따로 저장해야 한다.
        trRepository.save(eqp1Tr);
        trDetRepository.saveAll(eqp1TrDets);

        log.info("eqp1Tr : {} {} {}", eqp1Tr.getId(), eqp1Tr.getName(), eqp1Tr.getEqp1TrDets().size());
        eqp1TrDets.forEach(o ->
                log.info("eqp1TrDets : {} {} {}", o.getId(), o.getCol1(), o.getCreatedDate())
        );

        return eqp1Tr;
    }
}
