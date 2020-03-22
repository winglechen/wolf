package study.daydayup.wolf.business.pay.biz.service.india.razorpay;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.pay.api.config.india.RazorConfig;
import study.daydayup.wolf.business.pay.api.dto.base.payout.PayoutRequest;
import study.daydayup.wolf.business.pay.api.dto.base.payout.PayoutResponse;
import study.daydayup.wolf.business.pay.biz.domain.service.PayoutManager;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.pay.biz.service.india.razorpay
 *
 * @author Wingle
 * @since 2020/2/29 11:41 下午
 **/
@Component
public class RazorPayout implements PayoutManager {
    @Resource
    private RazorConfig config;

    @Override
    public PayoutResponse payout(@NonNull PayoutRequest request) {
        return null;
    }
}
