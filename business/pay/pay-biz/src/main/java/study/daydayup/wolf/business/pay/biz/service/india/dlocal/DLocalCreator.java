package study.daydayup.wolf.business.pay.biz.service.india.dlocal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.pay.biz.domain.service.AbstractPaymentCreator;
import study.daydayup.wolf.business.pay.biz.domain.service.PaymentCreator;

/**
 * study.daydayup.wolf.business.pay.biz.service.india.dlocal
 *
 * @author Wingle
 * @since 2020/6/28 6:37 下午
 **/
@Slf4j
@Component
public class DLocalCreator extends AbstractPaymentCreator implements PaymentCreator {
    @Override
    public void callPayEpi() {

    }

    @Override
    public void parseCreateResponse() {

    }
}
