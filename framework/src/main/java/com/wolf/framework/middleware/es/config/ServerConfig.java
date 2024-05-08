package com.wolf.framework.middleware.es.config;

import lombok.Data;
import com.wolf.framework.layer.api.Config;

/**
 * com.wolf.framework.middleware.es.bootstrap
 *
 * @author Wingle
 * @since 2021/12/7 上午1:44
 **/
@Data
public class ServerConfig implements Config {
    private String hostname;
    private String port;
    private String protocol;
    private String username;
    private String password;
}

