package me.kalpha.dtoentityjpa.tr.service;

import me.kalpha.dtoentityjpa.tr.dto.TrDto;
import me.kalpha.dtoentityjpa.tr.entity.TrEntity;
import me.kalpha.dtoentityjpa.tr.entity.TrMaterialCodeEntity;
import me.kalpha.dtoentityjpa.tr.repository.TrMaterialCodeRepository;
import me.kalpha.dtoentityjpa.tr.repository.TrRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TrService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    TrRepository trRepository;
    @Autowired
    TrMaterialCodeRepository trCodeRepository;

    public void save(TrDto trDto) {
        TrEntity trEntity = modelMapper.map(trDto, TrEntity.class);
        trRepository.save(trEntity);

        Set<TrMaterialCodeEntity> set = trDto.getCodeSet().stream()
                .map(e -> modelMapper.map(e, TrMaterialCodeEntity.class))
                .collect(Collectors.toSet());

        trCodeRepository.saveAll(set);
    }
}
