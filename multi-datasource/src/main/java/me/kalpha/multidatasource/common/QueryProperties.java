package me.kalpha.multidatasource.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Getter @Setter
@Configuration
@ConfigurationProperties(prefix = "query")
public class QueryProperties {
    private int samplesCount;
}
