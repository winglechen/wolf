package study.daydayup.wolf.business.pay.biz.service.india.razorpay;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.pay.api.config.india.RazorConfig;
import study.daydayup.wolf.business.pay.api.dto.base.PayRequest;
import study.daydayup.wolf.business.pay.api.dto.base.PayResponse;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.pay.biz.service.india.razorpay
 *
 * @author Wingle
 * @since 2020/2/29 11:41 下午
 **/
@Component
public class RazorPayer {
    @Resource
    private RazorConfig config;

    public PayResponse pay(@NonNull PayRequest request) {
        return null;
    }
}
