package com.wolf.framework.layer.web.session;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "wolf.session", ignoreInvalidFields = true)
public class SessionConfig {
    private Boolean enable = false;
    private String cookieKey = "WOLF_SESSION_ID";
    private long sessionMaxAge = 60 * 60 * 24 * 7;

    private int cookieMaxAge = 60 * 60 * 24 * 7;
    private String domain;
    private String path = "/";
    private boolean isHttpOnly = true;
    private boolean isSecure = true;
}
