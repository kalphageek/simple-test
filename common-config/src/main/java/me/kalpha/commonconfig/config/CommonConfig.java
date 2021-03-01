package me.kalpha.commonconfig.config;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.unit.DataSize;

@Getter @Setter
@ConfigurationProperties(prefix = "app.config")
public class CommonConfig {
    private String message;
    private DataSize chickenSize;
}
