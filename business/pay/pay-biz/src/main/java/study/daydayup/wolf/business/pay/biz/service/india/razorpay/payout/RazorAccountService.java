package study.daydayup.wolf.business.pay.biz.service.india.razorpay.payout;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.business.pay.api.dto.base.payout.PayoutRequest;
import study.daydayup.wolf.business.pay.biz.service.india.razorpay.model.RazorAccount;
import study.daydayup.wolf.framework.layer.domain.Service;

/**
 * study.daydayup.wolf.business.pay.biz.service.india.razorpay.payout
 *
 * @author Wingle
 * @since 2020/3/23 1:06 上午
 **/
@Component
public class RazorAccountService implements Service {
    private PayoutRequest request;
    public RazorAccount find(@Validated PayoutRequest request) {
        this.request = request;

        return null;
    }

    private RazorAccount findFromDb() {
        return null;
    }

    private void createContact() {

    }

    private void saveContactInfo() {

    }

    private void creatFundAccount() {

    }

    private void saveFundAccountInfo() {
        
    }
}
