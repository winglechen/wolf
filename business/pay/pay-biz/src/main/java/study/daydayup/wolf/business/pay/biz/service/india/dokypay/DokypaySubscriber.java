package study.daydayup.wolf.business.pay.biz.service.india.dokypay;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.pay.biz.domain.service.AbstractPaymentSubscriber;
import study.daydayup.wolf.business.pay.biz.domain.service.PaymentSubscriber;

/**
 * study.daydayup.wolf.business.pay.biz.service.india.dokypay
 *
 * @author Wingle
 * @since 2020/4/29 6:58 下午
 **/
@Slf4j
@Component
public class DokypaySubscriber extends AbstractPaymentSubscriber implements PaymentSubscriber {
    public int subscribe(String data) {
        return 0;
    }
}
