package study.daydayup.wolf.framework.middleware.hbase.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;


/**
 * HBaseConnect
 *
 * @author rocky <luojing@onion-pay.com>
 * @since 2023/7/25 16:54
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "wolf.hbase", ignoreInvalidFields = true)
public class HBaseConfigProperties {

    private Map<String,HBaseServerConfig> servers;


}
