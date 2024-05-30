package org.development.routems.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "ai-service")
@Data
public class AIServiceConfig {
    private String ip;
    private int port;
}