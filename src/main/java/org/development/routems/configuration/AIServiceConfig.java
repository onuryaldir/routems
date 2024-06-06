package org.development.routems.configuration;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "ai-service")
@RequiredArgsConstructor
@Data
public class AIServiceConfig {
    private String ip;
    private int port;
}