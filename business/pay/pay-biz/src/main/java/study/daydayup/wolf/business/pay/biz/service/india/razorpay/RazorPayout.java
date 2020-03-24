package study.daydayup.wolf.business.pay.biz.service.india.razorpay;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.business.pay.api.config.india.RazorConfig;
import study.daydayup.wolf.business.pay.api.domain.enums.PaymentMethodEnum;
import study.daydayup.wolf.business.pay.api.domain.exception.PayoutAccountNotFoundException;
import study.daydayup.wolf.business.pay.api.dto.base.payout.PayoutRequest;
import study.daydayup.wolf.business.pay.api.dto.base.payout.PayoutResponse;
import study.daydayup.wolf.business.pay.biz.domain.service.PayoutManager;
import study.daydayup.wolf.business.pay.biz.service.india.razorpay.model.RazorAccount;
import study.daydayup.wolf.business.pay.biz.service.india.razorpay.payout.RazorAccountService;
import study.daydayup.wolf.business.pay.biz.service.india.razorpay.payout.RazorPayoutService;
import study.daydayup.wolf.common.util.lang.StringUtil;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.pay.biz.service.india.razorpay
 *
 * @author Wingle
 * @since 2020/2/29 11:41 下午
 **/
@Component
public class RazorPayout  {
    @Resource
    private RazorConfig config;

    @Resource
    private RazorAccountService accountService;
    @Resource
    private RazorPayoutService payoutService;

    private PayoutRequest request;
    private RazorAccount account;

    public PayoutResponse payout(@Validated PayoutRequest request) {
        this.request = request;
        this.request.setPaymentMethod(PaymentMethodEnum.RAZOR_PAYOUT.getCode());

        validRequest();
        findPayoutAccount();
        return doPayout();
    }

    private void validRequest() {

    }

    private void findPayoutAccount() {
        account = accountService.find(request);
        if (account == null || StringUtil.isEmpty(account.getAccountId())) {
            throw new PayoutAccountNotFoundException();
        }
    }

    private PayoutResponse doPayout() {
        return payoutService.create(account, request);
    }

}
