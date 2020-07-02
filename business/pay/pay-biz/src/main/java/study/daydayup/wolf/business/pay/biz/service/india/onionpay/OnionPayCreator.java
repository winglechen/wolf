package study.daydayup.wolf.business.pay.biz.service.india.onionpay;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.pay.biz.domain.service.AbstractPaymentCreator;
import study.daydayup.wolf.business.pay.biz.domain.service.PaymentCreator;

/**
 * study.daydayup.wolf.business.pay.biz.service.india.onionpay
 *
 * @author Wingle
 * @since 2020/7/2 4:10 下午
 **/
@Slf4j
@Component
public class OnionPayCreator extends AbstractPaymentCreator implements PaymentCreator {
    @Override
    public void callPayEpi() {

    }

    @Override
    public void parseCreateResponse() {

    }
}
