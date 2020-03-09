package study.daydayup.wolf.business.pay.biz.epi;

import lombok.NonNull;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.pay.api.domain.entity.Payment;
import study.daydayup.wolf.business.pay.biz.domain.entity.PaymentEntity;
import study.daydayup.wolf.business.trade.api.dto.buy.base.TradeNotification;
import study.daydayup.wolf.business.trade.api.dto.buy.base.TradeNotificationResponse;
import study.daydayup.wolf.business.trade.api.service.buy.PaySubscriber;

/**
 * study.daydayup.wolf.business.pay.biz.epi
 *
 * @author Wingle
 * @since 2020/3/1 12:31 上午
 **/
@Component
public class DefaultTradeNotifier implements TradeNotifier {
    @Reference
    private PaySubscriber subscriber;

    @Override
    public int notify(@NonNull PaymentEntity paymentEntity) {
        TradeNotification notification = getNotification(paymentEntity);
        if (notification == null) {
            return 0;
        }

        TradeNotificationResponse response = subscriber.subscribe(notification).notNullData();
        if (response.isSuccess()) {
            return 1;
        }

        return response.getCause();
    }

    private TradeNotification getNotification(PaymentEntity paymentEntity) {
        TradeNotification notification = new TradeNotification();
        Payment payment = paymentEntity.getModel();
        if (payment == null) {
            return null;
        }

        BeanUtils.copyProperties(payment, notification);
        notification.setPaidAt(payment.getUpdateAt());

        return notification;
    }

}
