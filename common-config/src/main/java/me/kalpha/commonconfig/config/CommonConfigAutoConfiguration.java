package me.kalpha.commonconfig.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfigAutoConfiguration {
//    private CommonConfig commonConfig;
//
//    @Autowired
//    public CommonConfigAutoConfiguration(CommonConfig commonConfig) {
//        this.commonConfig = commonConfig;
//    }

    @ConditionalOnMissingBean
    @Bean
    public CommonConfig commonConfig() {
        CommonConfig commonConfig = new CommonConfig();
        return commonConfig;
    }
}
