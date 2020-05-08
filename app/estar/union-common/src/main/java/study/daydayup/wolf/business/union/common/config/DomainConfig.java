package study.daydayup.wolf.business.union.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * study.daydayup.wolf.business.union.common.config
 *
 * @author Wingle
 * @since 2020/4/22 4:29 下午
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "wolf.domain")
public class DomainConfig {
    private Map<String, String > file;
}
