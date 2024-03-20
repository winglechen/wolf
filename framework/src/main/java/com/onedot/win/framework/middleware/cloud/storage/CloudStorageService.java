package com.onedot.win.framework.middleware.cloud.storage;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.onedot.win.common.io.file.FileUtil;
import com.onedot.win.common.util.lang.StringUtil;
import com.onedot.win.common.util.time.DateUtil;

import javax.annotation.Resource;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class CloudStorageService {

    private static final int SIGNATURE_EXPIRE_TIME = 300;
    private static final int URL_EXPIRE_TIME = 3600;
    private static final long MIN_CONTENT_LENGTH = 0;
    private static final long MAX_CONTENT_LENGTH = 1048576000;

    private static final String TEMP_PATH = "/tmp";

    @Resource
    private CloudStorageConfig cloudStorageConfig;

    @Resource
    private AliyunSecurityTokenService aliyunSecurityTokenService;

    public String generateName(String filePath) {
        return StringUtil.join(StringUtil.uuid(), FileUtil.getExtension(filePath, true));
    }


    public Map<String, String> signature(String bucket, String dir) {
        OSS client;
        try {
            BucketConfig bucketConfig = cloudStorageConfig.getConfigByBucket(bucket);
            String endpoint = bucketConfig.getEndpoint();
            String accessKeyId = bucketConfig.getAccessKeyId();
            String accessKeySecret = bucketConfig.getAccessKeySecret();
            client = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            PolicyConditions policyConditions = createPolicyConditions(dir);
            LocalDateTime expire = LocalDateTime.now().plusSeconds(SIGNATURE_EXPIRE_TIME);
            String policy = client.generatePostPolicy(DateUtil.asDate(expire), policyConditions);

            String signature = client.calculatePostSignature(policy);
            String encodedPolicy = encodePolicy(policy);

            return formatSignature(bucket, bucketConfig, dir, signature, encodedPolicy, expire);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }

    private PolicyConditions createPolicyConditions(String path) {
        PolicyConditions policy = new PolicyConditions();
        policy.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, MIN_CONTENT_LENGTH, MAX_CONTENT_LENGTH);

        if (null != path) {
            policy.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, path);
        }

        return policy;
    }

    private String encodePolicy(@NonNull String policyString) {
        byte[] binaryData = policyString.getBytes(StandardCharsets.UTF_8);
        return BinaryUtil.toBase64String(binaryData);
    }

    private Map<String, String> formatSignature(String bucket, BucketConfig bucketConfig, String dir, @NonNull String signature, @NonNull String policy, LocalDateTime expire) {
        Map<String, String> args = new HashMap<>(8);
        args.put("accessId", bucketConfig.getAccessKeyId());
        args.put("policy", policy);
        args.put("signature", signature);
        args.put("dir", dir);
        args.put("hostname", StringUtil.join("//", bucketConfig.getBucketName(), ".", bucketConfig.getEndpoint()));
        args.put("bucket", bucket);
        args.put("bucketName", bucketConfig.getBucketName());
        args.put("expire", Timestamp.valueOf(expire).toString());

        return args;
    }

    public String upload(String objectName, String filePath, String bucket) {
        File file = new File(filePath);
        return upload(objectName, file, bucket);
    }

    public String upload(String objectName, @NonNull File file, String bucket) {
        if (!file.exists()) {
            log.error("upload file not exist===>{}", file);
            return null;
        }

        OSS client = null;
        try {
            BucketConfig bucketConfig = cloudStorageConfig.getConfigByBucket(bucket);
            String endpoint = bucketConfig.getEndpoint();
            String accessKeyId = bucketConfig.getAccessKeyId();
            String accessKeySecret = bucketConfig.getAccessKeySecret();
            String bucketName = bucketConfig.getBucketName();
            client = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            client.putObject(bucketName, objectName, file);

            log.info("File was uploaded successfully===>{} {}", file, objectName);
            return buildUrl(bucket, objectName);
        } catch (OSSException e) {
            log.error("upload file failed===>{}", file);
            e.printStackTrace();
        } finally {
            if (null != client) {
                client.shutdown();
            }
        }

        return null;
    }

    public String generateLink(@NonNull String objectName, String bucket) {
        JSONObject sts = aliyunSecurityTokenService.requestOssCredentials(bucket);
        JSONObject credentials = sts.getJSONObject("credentials");
        if (null == credentials) {
            return null;
        }

        String accessKeyId = credentials.getString("accessKeyId");
        String accessKeySecret = credentials.getString("accessKeySecret");
        String securityToken = credentials.getString("securityToken");

        OSS client = null;
        try {
            BucketConfig bucketConfig = cloudStorageConfig.getConfigByBucket(bucket);

            String endpoint = StringUtil.notEmpty(bucketConfig.getCustomDomain())
                    ? bucketConfig.getCustomDomain()
                    : bucketConfig.getEndpoint();
            client = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret, securityToken);
            Date expiration = new Date(new Date().getTime() + 3600 * 1000);
            URL url = client.generatePresignedUrl(bucketConfig.getBucketName(), objectName, expiration);
            return url.toString();
        } catch (OSSException e) {
            e.printStackTrace();
        } finally {
            if (null != client) {
                client.shutdown();
            }
        }

        return null;
    }


    public Map<String, String> parseLink(String link) {
        Map<String, String> map = new HashMap<>(2);
        try {
            URI uri = new URI(link);
            map.put("bucket", uri.getHost());
            map.put("objectName", StringUtil.ltrim(uri.getPath(), "/"));
        } catch (URISyntaxException e) {
            log.error(e.getMessage(), e);
        }

        return map;
    }

    private String buildUrl(@NonNull String bucket, @NonNull String objectName) {
        return StringUtil.join("oss://", bucket, "/", objectName);
    }


    public File download(String objectName, String bucket) {
        String extension = FileUtil.getExtension(objectName, true);
        String name = StringUtil.join(StringUtil.uuid(), extension);
        String path = StringUtil.joinWith("/", TEMP_PATH, name);
        return download(objectName, bucket, path);
    }


    public File download(String objectName, String bucket, String path) {
        File file = new File(path);
        if (file.exists()) {
            return file;
        }

        try {
            BucketConfig bucketConfig = cloudStorageConfig.getConfigByBucket(bucket);
            OSS client = new OSSClientBuilder().build(bucketConfig.getEndpoint(),
                    bucketConfig.getAccessKeyId(),
                    bucketConfig.getAccessKeySecret()
            );

            client.getObject(new GetObjectRequest(bucketConfig.getBucketName(), objectName), file);
            return file;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
