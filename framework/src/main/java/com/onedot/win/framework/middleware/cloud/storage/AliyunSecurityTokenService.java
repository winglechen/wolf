package com.onedot.win.framework.middleware.cloud.storage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.auth.sts.AssumeRoleRequest;
import com.aliyuncs.auth.sts.AssumeRoleResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import com.onedot.win.common.util.lang.StringUtil;

import javax.annotation.Resource;
import java.time.Duration;

@Slf4j
@Component
public class AliyunSecurityTokenService {

    private static final int CACHE_EXPIRE_MINUTES = 30;
    private static final String CACHE_KEY = "STS:OSS:";

    @Resource
    private CloudStorageConfig cloudStorageConfig;

    @Resource
    private StringRedisTemplate stringRedisTemplate;


    public JSONObject requestOssCredentials(String bucket) {
        String cachedSts = cacheGet(bucket);
        if (StringUtil.notEmpty(cachedSts)) {
            return JSON.parseObject(cachedSts);
        }
        BucketConfig bucketConfig = cloudStorageConfig.getConfigByBucket(bucket);

        DefaultProfile profile = DefaultProfile.getProfile(
                bucketConfig.getRegionId(),
                bucketConfig.getAccessKeyId(),
                bucketConfig.getAccessKeySecret()
        );
        IAcsClient client = new DefaultAcsClient(profile);

        AssumeRoleRequest request = new AssumeRoleRequest();
        request.setRoleArn(bucketConfig.getRoleArn());
        request.setRoleSessionName(bucketConfig.getRoleSessionName());

        JSONObject result = null;
        try {
            AssumeRoleResponse response = client.getAcsResponse(request);
            result = (JSONObject) JSON.toJSON(response);
            cacheSet(bucket, result.toJSONString());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
            e.printStackTrace();
        }

        return result;
    }

    private String cacheGet(String bucket) {
        String key = StringUtil.join(CACHE_KEY, bucket.toUpperCase());
        return stringRedisTemplate.opsForValue().get(key);
    }

    private void cacheSet(String bucket, String data) {
        String key = StringUtil.join(CACHE_KEY, bucket.toUpperCase());
        stringRedisTemplate.opsForValue().set(key, data, Duration.ofMinutes(CACHE_EXPIRE_MINUTES));
    }

}
