package me.kalpha.commonconfig.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.unit.DataSize;

@Setter @Getter
@ConfigurationProperties(prefix = "app.config")
public class CommonConfig {
    private String message;
    private DataSize chickenSize;
}
