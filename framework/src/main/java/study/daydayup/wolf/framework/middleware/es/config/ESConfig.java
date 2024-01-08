package study.daydayup.wolf.framework.middleware.es.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import study.daydayup.wolf.framework.layer.api.Config;
import study.daydayup.wolf.framework.middleware.es.bootstrap.ESBootstrap;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * study.daydayup.wolf.framework.middleware.es.bootstrap
 *
 * @author Wingle
 * @since 2021/12/7 上午1:44
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "wolf.es", ignoreInvalidFields = true)
public class ESConfig implements Config {
    /**
     * the switch of es service
     * default false
     */
    private boolean enable = false;

    /**
     * server config map
     */
    private Map<String, ServerConfig> server;

    /**
     * indexLocator
     * key: server name
     * value: index set
     */
    private Map<String, Set<String>> indexLocator;

    @PostConstruct
    public void bootstrap() {
        ESBootstrap.bootstrap(this);
    }

    public Map<String, ServerConfig> getServer() {
        if (server == null) {
            server = new HashMap<>();
        }

        return server;
    }

    public Map<String, Set<String>> getIndexLocator() {
        if (indexLocator == null) {
            indexLocator = new HashMap<>();
        }

        return indexLocator;
    }
}
