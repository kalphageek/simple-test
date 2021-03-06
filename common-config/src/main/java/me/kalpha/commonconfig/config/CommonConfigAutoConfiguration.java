package me.kalpha.commonconfig.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfigAutoConfiguration {
    @ConditionalOnMissingBean
    @Bean
    public CommonConfig commonConfig() {
        return new CommonConfig();
    }
}
