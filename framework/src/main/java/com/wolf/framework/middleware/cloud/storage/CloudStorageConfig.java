package com.wolf.framework.middleware.cloud.storage;

import lombok.Data;
import lombok.NonNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import com.wolf.framework.layer.api.Config;

@Data
@Configuration
@ConfigurationProperties(prefix = "wolf.cloud.storage")
public class CloudStorageConfig implements Config {
    BucketConfig exported;
    BucketConfig uploaded;

    public BucketConfig getConfigByBucket(@NonNull String bucket) {
        BucketConfig result;
        switch (bucket) {
            case "exported":
                result = exported;
                break;
            case "uploaded":
                result = uploaded;
                break;
            default:
                throw new InvalidCloudStorageConfigException("ali oss get bucket failed");
        }

        return result;
    }
}
