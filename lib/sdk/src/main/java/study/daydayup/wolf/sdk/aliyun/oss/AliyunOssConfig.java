package study.daydayup.wolf.sdk.aliyun.oss;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * study.daydayup.wolf.sdk.aliyun.oss
 *
 * @author Wingle
 * @since 2020/3/25 12:42 下午
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "wolf.aliyun.oss")
public class AliyunOssConfig {
    private String accessId;
    private String accessKey;

    private String endpoint;
    private String defaultBucket;

    private String rootPath;
}
