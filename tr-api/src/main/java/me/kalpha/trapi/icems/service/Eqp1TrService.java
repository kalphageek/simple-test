package me.kalpha.trapi.icems.service;

import me.kalpha.trapi.icems.entity.Eqp1Tr;
import me.kalpha.trapi.icems.repository.Eqp1TrDetRepository;
import me.kalpha.trapi.icems.repository.Eqp1TrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Eqp1TrService {
    @Autowired
    Eqp1TrRepository trRepository;
    @Autowired
    Eqp1TrDetRepository trDetRepository;

    public Eqp1Tr saveTr(Eqp1Tr eqp1Tr) {
        trRepository.save(eqp1Tr);
        trDetRepository.saveAll(eqp1Tr.getEqp1TrDets());
        return eqp1Tr;
    }
}
