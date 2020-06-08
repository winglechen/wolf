package study.daydayup.wolf.business.pay.biz.service.india.razorpay.handler;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.pay.biz.domain.service.AbstractNotificationHandler;
import study.daydayup.wolf.business.pay.biz.domain.service.NotificationHandler;

/**
 * study.daydayup.wolf.business.pay.biz.service.india.razorpay.handler
 *
 * @author Wingle
 * @since 2020/3/7 8:47 下午
 **/
@Component
public class RazorPaidHandler extends AbstractNotificationHandler implements NotificationHandler {
    @Override
    public boolean isSuccess () {
        return getNotification().getStatus().equalsIgnoreCase("paid");
    }
}
