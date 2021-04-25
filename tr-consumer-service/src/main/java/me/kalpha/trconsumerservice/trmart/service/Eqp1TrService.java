package me.kalpha.trconsumerservice.trmart.service;

import lombok.extern.slf4j.Slf4j;
import me.kalpha.trconsumerservice.trmart.entity.Eqp1Tr;
import me.kalpha.trconsumerservice.trmart.entity.Eqp1TrDet;
import me.kalpha.trconsumerservice.trmart.repository.Eqp1TrDetRepository;
import me.kalpha.trconsumerservice.trmart.repository.Eqp1TrRepository;
import org.modelmapper.ModelMapper;
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
    ModelMapper modelMapper;

    @Transactional
    public Eqp1Tr createTr(Eqp1Tr eqp1TrDto) {
        Eqp1Tr eqp1Tr = modelMapper.map(eqp1TrDto, Eqp1Tr.class);

        log.info("eqp1Tr : {} {}", eqp1Tr.getEqp1TrDets(), eqp1Tr.getName());
        List<Eqp1TrDet> eqp1TrDets = eqp1TrDto.getEqp1TrDets().stream()
                .map(o -> modelMapper.map(o, Eqp1TrDet.class))
                .collect(Collectors.toList());

        // Save at Eqp1Tr, Eqp1TrDet
        // CascadeType이 지정되어 있지 않기 때문에 따로 저장해야 한다.
        trRepository.save(eqp1Tr);
        trDetRepository.saveAll(eqp1TrDets);

        return eqp1Tr;
    }
}
