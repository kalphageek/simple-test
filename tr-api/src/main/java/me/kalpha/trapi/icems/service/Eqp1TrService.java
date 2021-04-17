package me.kalpha.trapi.icems.service;

import me.kalpha.trapi.icems.entity.Eqp1Tr;
import me.kalpha.trapi.icems.entity.Eqp1TrDet;
import me.kalpha.trapi.icems.entity.Eqp1TrDetDto;
import me.kalpha.trapi.icems.entity.Eqp1TrDto;
import me.kalpha.trapi.icems.repository.Eqp1TrDetRepository;
import me.kalpha.trapi.icems.repository.Eqp1TrRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Eqp1TrService {
    @Autowired
    Eqp1TrRepository trRepository;
    @Autowired
    Eqp1TrDetRepository trDetRepository;
    @Autowired
    ModelMapper trMapper;
    @Autowired
    ModelMapper trDetMapper;

    public Eqp1Tr createTr(Eqp1TrDto eqp1TrDto) {

        Eqp1Tr eqp1Tr = trMapper.map(eqp1TrDto, Eqp1Tr.class);
        List<Eqp1TrDet> eqp1TrDets = Arrays.asList(trMapper.map(eqp1TrDto.getEqp1TrDetDtos(), Eqp1TrDet[].class));
//        List<Eqp1TrDet> eqp1TrDets = eqp1TrDto.getEqp1TrDetDtos().stream()
//                .map(o -> trMapper.map(o, Eqp1TrDet.class))
//                .collect(Collectors.toList());

        eqp1TrDets.forEach(o -> o.assignEqp1Tr(eqp1Tr));

        trDetRepository.saveAll(eqp1Tr.getEqp1TrDets());

        return eqp1Tr;
    }
}
