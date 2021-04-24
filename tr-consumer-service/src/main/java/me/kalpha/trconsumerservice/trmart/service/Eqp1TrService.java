package me.kalpha.trconsumerservice.trmart.service;

import lombok.extern.slf4j.Slf4j;
import me.kalpha.trconsumerservice.trmart.entity.Eqp1Tr;
import me.kalpha.trconsumerservice.trmart.entity.Eqp1TrDet;
import me.kalpha.trconsumerservice.trmart.repository.Eqp1TrDetRepository;
import me.kalpha.trconsumerservice.trmart.repository.Eqp1TrRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class Eqp1TrService {
    @Autowired
    Eqp1TrRepository trRepository;
    @Autowired
    Eqp1TrDetRepository trDetRepository;

    public Eqp1Tr createTr(Eqp1Tr eqp1Tr) {
        log.info("eqp1Tr : {} {}", eqp1Tr.getEqp1TrDets(), eqp1Tr.getName());
        List<Eqp1TrDet> eqp1TrDets = eqp1Tr.getEqp1TrDets().stream()
                .collect(Collectors.toList());

        // Save at Eqp1Tr, Eqp1TrDet
        trRepository.save(eqp1Tr);
        trDetRepository.saveAll(eqp1TrDets);

        return eqp1Tr;
    }
}
