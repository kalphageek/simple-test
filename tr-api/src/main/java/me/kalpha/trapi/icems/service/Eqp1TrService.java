package me.kalpha.trapi.icems.service;

import me.kalpha.trapi.icems.entity.Eqp1Tr;
import me.kalpha.trapi.icems.entity.Eqp1TrDet;
import me.kalpha.trapi.icems.entity.Eqp1TrDto;
import me.kalpha.trapi.icems.repository.Eqp1TrDetRepository;
import me.kalpha.trapi.icems.repository.Eqp1TrRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Eqp1TrService {
    @Autowired
    Eqp1TrRepository trRepository;
    @Autowired
    Eqp1TrDetRepository trDetRepository;
    @Autowired
    ModelMapper modelMapper;

    public Eqp1Tr createTr(Eqp1TrDto eqp1TrDto) {
        Eqp1Tr eqp1Tr = modelMapper.map(eqp1TrDto, Eqp1Tr.class);
        List<Eqp1TrDet> eqp1TrDets = eqp1TrDto.getEqp1TrDetDtos().stream()
                .map(o -> o.toEntity(eqp1Tr))
                .collect(Collectors.toList());

        trRepository.save(eqp1Tr);
        trDetRepository.saveAll(eqp1Tr.getEqp1TrDets());

        return eqp1Tr;
    }
}
