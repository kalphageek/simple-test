package me.kalpha.jpatest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

/**
 * 자동으로 createBy, lastModifiedBy 를 설정해준다
 */
@EnableJpaAuditing
@Configuration
public class AuditConfiguration {
    @Bean
    AuditorAware<String> auditorAware() {
        // Session으로 부터 User ID를 리턴한다.
        return () -> Optional.of("jpa-test");
    }
}
