package com.wolf.framework.layer.web.auth;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "wolf.auth", ignoreInvalidFields = true)
public class AuthConfig {
    private String accountKey = "accountId";
    private String spaceKey = "orgId";
    private String spaceListKey = "spaceList";

    private Boolean enable = false;
    private String cookieKey = "WOLF_SESSION_ID";
    private long sessionMaxAge = 60 * 60 * 24 * 7;

    private int cookieMaxAge = 60 * 60 * 24 * 7;
    private String domain;
    private String path = "/";
    private boolean isHttpOnly = true;
    private boolean isSecure = true;

    private List<String> authPath = new ArrayList<>();
    private List<String> excludePath = new ArrayList<>();

    private String denyCode = "ACCESS_DENY";
    private String denyMessage = "Please Login, then retry.";
}
