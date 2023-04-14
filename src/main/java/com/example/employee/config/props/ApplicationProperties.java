package com.example.employee.config.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "app")
public class ApplicationProperties {
    private String jwtSecret;
    private Long jwtExpirationMs;
}
