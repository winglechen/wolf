package study.daydayup.wolf.sdk.aliyun.oss;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * study.daydayup.wolf.sdk.aliyun.oss
 *
 * @author Wingle
 * @since 2020/3/25 12:42 下午
 **/
@Configuration
@ConfigurationProperties(prefix = "wolf.aliyun.oss")
public class AliyunOssConfig {
    private String accessKeyId;
    private String accessKeySecret;
    private String securityToken;

    private String endpoint;
    private String bucketName;
}
