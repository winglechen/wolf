package study.daydayup.wolf.business.pay.biz.service.india.razorpay.payout;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.business.pay.api.config.india.RazorConfig;
import study.daydayup.wolf.business.pay.api.dto.base.payout.PayoutRequest;
import study.daydayup.wolf.business.pay.biz.service.india.razorpay.model.RazorAccount;
import study.daydayup.wolf.framework.layer.domain.Service;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.pay.biz.service.india.razorpay.payout
 *
 * @author Wingle
 * @since 2020/3/23 1:06 上午
 **/
@Component
public class RazorFundService implements Service {
    private RazorAccount account;
    private PayoutRequest payoutRequest;

    @Resource
    private RazorConfig config;

    public RazorAccount create(RazorAccount account, @Validated PayoutRequest request) {
        return null;
    }
}
