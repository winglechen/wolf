package study.daydayup.wolf.business.union.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import study.daydayup.wolf.sdk.aliyun.oss.AliyunOssUtil;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.union.admin.controller
 *
 * @author Wingle
 * @since 2019/12/10 7:16 下午
 **/
@RestController
public class UnionDemoController {
    @Resource
    private AliyunOssUtil ossUtil;

    @GetMapping("/show")
    public String show() {
        String str = "onionwallet-private://img/20200409/6831ebf9-b5ce-e492-533e-8bd5b5db7c3c.jpg";

        String url = ossUtil.encode(str);
        return url;
    }


}
