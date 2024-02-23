package study.daydayup.wolf.framework.middleware.cloud.storage;

import lombok.Data;
import study.daydayup.wolf.framework.layer.api.Config;

@Data
public class BucketConfig implements Config {
    private String accessKeyId;
    private String accessKeySecret;

    private String regionId;
    private String endpoint;
    private String customDomain;
    private String bucketName;

    private String roleArn;
    private String roleSessionName;
}
