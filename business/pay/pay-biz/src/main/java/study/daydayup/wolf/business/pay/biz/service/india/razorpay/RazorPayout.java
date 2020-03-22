package study.daydayup.wolf.business.pay.biz.service.india.razorpay;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
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

    private PayoutRequest request;

    @Override
    public PayoutResponse payout(@Validated PayoutRequest request) {
        this.request = request;

        return null;
    }

    private void getBankCard() {

    }

    private void checkContact() {

    }

    private void checkAccount() {

    }

    private void doPayout() {

    }


}
