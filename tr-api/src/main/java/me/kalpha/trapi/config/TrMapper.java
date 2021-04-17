package me.kalpha.trapi.config;

import me.kalpha.trapi.icems.entity.Eqp1Tr;
import me.kalpha.trapi.icems.entity.Eqp1TrDet;
import me.kalpha.trapi.icems.entity.Eqp1TrDetDto;
import me.kalpha.trapi.icems.entity.Eqp1TrDto;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TrMapper {
    private final ModelMapper trMapper = new ModelMapper();

    @Bean
    public ModelMapper trMapper() {
        trMapper.getConfiguration()
                .setPropertyCondition(Conditions.isNotNull()) //기존값을 Null로 매핑 방지
                .setMatchingStrategy(MatchingStrategies.STRICT) //정확히 일치하는 속성만 매핑
                .setSkipNullEnabled(true);

        return trMapper;
    }
}
