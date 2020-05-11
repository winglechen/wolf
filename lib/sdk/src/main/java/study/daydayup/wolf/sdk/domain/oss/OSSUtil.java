package study.daydayup.wolf.sdk.domain.oss;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.sdk.aliyun.oss.AliyunOssUtil;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.sdk.domain
 *
 * @author Wingle
 * @since 2020/5/11 2:17 下午
 **/
@Component
public class OSSUtil extends AbstractOSS implements OSS {
    @Resource
    private AliyunOssUtil aliyunOssUtil;
    @Override
    public String decode(@NonNull String str) {
        return null;
    }
}
