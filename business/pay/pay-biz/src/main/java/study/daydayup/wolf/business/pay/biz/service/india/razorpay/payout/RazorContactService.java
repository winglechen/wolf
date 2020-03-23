package study.daydayup.wolf.business.pay.biz.service.india.razorpay.payout;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.business.pay.api.dto.base.payout.PayoutRequest;
import study.daydayup.wolf.business.pay.biz.service.india.razorpay.dto.ContactRequest;
import study.daydayup.wolf.business.pay.biz.service.india.razorpay.model.RazorAccount;
import study.daydayup.wolf.framework.layer.domain.Service;

/**
 * study.daydayup.wolf.business.pay.biz.service.india.razorpay.payout
 *
 * @author Wingle
 * @since 2020/3/23 1:06 上午
 **/
@Component
public class RazorContactService implements Service {
    private RazorAccount account;
    private PayoutRequest request;

    public RazorAccount create(RazorAccount account, @Validated PayoutRequest request) {
        initAccount(account);

        return this.account;
    }

    private void initAccount(RazorAccount account) {
        if (null == account) {
            this.account = new RazorAccount();
        } else {
            this.account = account;
        }
    }

    private ContactRequest createRequest() {
        return null;
    }

    private void callEpi() {

    }
}
