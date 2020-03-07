package study.daydayup.wolf.business.pay.biz.domain.entity;

import study.daydayup.wolf.business.pay.api.domain.entity.PayNotification;
import study.daydayup.wolf.business.pay.api.domain.entity.Payment;
import study.daydayup.wolf.business.pay.api.domain.enums.PaymentStateEnum;
import study.daydayup.wolf.business.trade.api.domain.util.StateUtil;
import study.daydayup.wolf.common.util.lang.BeanUtil;
import study.daydayup.wolf.framework.layer.domain.AbstractEntity;
import study.daydayup.wolf.framework.layer.domain.Entity;

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

        changes.setOutTradeNo(notification.getOutTradeNo());
        return true;
    }
}
