package study.daydayup.wolf.business.pay.biz.service.india.razorpay.handler;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.pay.api.domain.entity.PayNotification;
import study.daydayup.wolf.business.pay.api.service.NotificationHandler;

/**
 * study.daydayup.wolf.business.pay.biz.service.india.razorpay.handler
 *
 * @author Wingle
 * @since 2020/3/7 8:46 下午
 **/

@Component
public class PayoutHandler implements NotificationHandler {
    @Override
    public int handle(PayNotification notification) {
        return 0;
    }
}
