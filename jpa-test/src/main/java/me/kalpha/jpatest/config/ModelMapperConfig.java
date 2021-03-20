package me.kalpha.jpatest.config;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    private final ModelMapper modelMapper = new ModelMapper();
    @Bean
    public ModelMapper modelMapper() {
//        이름이 정확한 속성만 매핑, 값이 Null인 속성은 매핑에서 제외
        modelMapper.getConfiguration()
                .setPropertyCondition(Conditions.isNotNull()) //기존값을 Null로 매핑 방지
                .setMatchingStrategy(MatchingStrategies.STRICT) //정확히 일치하는 속성만 매핑
                .setSkipNullEnabled(true);
        return modelMapper;
    }
}
