package study.daydayup.wolf.business.union.task.service.impl;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.union.task.dts.source.VerifyCodeSource;
import study.daydayup.wolf.business.union.task.service.DailySmsService;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.union.task.service.impl
 *
 * @author Wingle
 * @since 2020/6/20 5:26 下午
 **/
@Component
public class DailySmsServiceImpl implements DailySmsService {
    @Resource
    private VerifyCodeSource verifyCodeSource;

    @Override
    public void countVerifyCodeSms() {

    }
}
