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

    private List<String> authPath = new ArrayList<>();
    private List<String> excludePath = new ArrayList<>();

    private String denyCode = "";
    private String denyMessage = "";
}
