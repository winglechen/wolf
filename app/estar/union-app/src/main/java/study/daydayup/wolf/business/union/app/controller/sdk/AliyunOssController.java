package study.daydayup.wolf.business.union.app.controller.sdk;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.sdk.aliyun.oss.AliyunOssUtil;

import javax.annotation.Resource;
import java.util.Map;

/**
 * study.daydayup.wolf.business.union.admin.controller.sdk
 *
 * @author Wingle
 * @since 2020/3/26 7:42 下午
 **/
@RestController
public class AliyunOssController {
    @Resource
    private AliyunOssUtil ossUtil;

    @GetMapping("/sdk/aliyun/oss/signature")
    public Result<Map<String, String>> createSignature() {
        Map<String, String> signature = ossUtil.createSignature();

        return Result.ok(signature);
    }
}
