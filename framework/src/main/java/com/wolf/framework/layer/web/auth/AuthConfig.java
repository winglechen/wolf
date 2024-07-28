package com.wolf.framework.layer.web.auth;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "wolf.auth")
public class AuthConfig {

    private String sessionId = "WOLF_SESSION_ID";
}
