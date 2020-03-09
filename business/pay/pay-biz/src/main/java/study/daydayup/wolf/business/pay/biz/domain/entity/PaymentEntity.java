package study.daydayup.wolf.business.pay.biz.domain.entity;

import study.daydayup.wolf.business.pay.api.domain.entity.PayNotification;
import study.daydayup.wolf.business.pay.api.domain.entity.Payment;
import study.daydayup.wolf.business.pay.api.domain.enums.PaymentStateEnum;
import study.daydayup.wolf.business.trade.api.domain.util.StateUtil;
import study.daydayup.wolf.common.util.lang.BeanUtil;
import study.daydayup.wolf.framework.layer.domain.AbstractEntity;
import study.daydayup.wolf.framework.layer.domain.Entity;

import java.time.LocalDateTime;

/**
 * study.daydayup.wolf.business.pay.biz.domain.entity
 *
 * @author Wingle
 * @since 2020/2/28 11:03 上午
 **/
public class PaymentEntity extends AbstractEntity<Payment> implements Entity {
    public PaymentEntity(Payment payment) {
        model = payment;
        key = Payment.builder()
           .paymentNo(payment.getPaymentNo())
           .build();
        isNew = false;
    }

    public boolean isPayable() {
        return BeanUtil.equals(model.getState(), PaymentStateEnum.WAIT_TO_PAY.getCode());
    }

    private void initChanges() {
        if (changes != null) {
            return;
        }
        changes = new Payment();
    }

    public boolean handleSuccessNotification(PayNotification notification) {
        if (isPayable()) {
            return false;
        }

        LocalDateTime now = LocalDateTime.now();
        key.setState(model.getState());

        model.setState(PaymentStateEnum.PAID.getCode());
        model.setOutTradeNo(notification.getOutTradeNo());
        model.setUpdateAt(now);

        changes.setState(PaymentStateEnum.PAID.getCode());
        changes.setOutTradeNo(notification.getOutTradeNo());
        changes.setUpdateAt(now);
        return true;
    }
}
