package study.daydayup.wolf.business.pay.biz.domain.service;

import lombok.Data;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.pay.api.domain.entity.PayNotification;
import study.daydayup.wolf.business.pay.api.domain.entity.Payment;
import study.daydayup.wolf.business.pay.api.domain.enums.NotifyReturnEnum;
import study.daydayup.wolf.business.pay.biz.domain.entity.PaymentEntity;
import study.daydayup.wolf.business.pay.biz.domain.repository.PaymentRepository;
import study.daydayup.wolf.business.pay.biz.epi.DefaultTradeNotifier;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.pay.biz.domain.service
 *
 * @author Wingle
 * @since 2020/3/7 9:08 下午
 **/
@Data
@Component
public abstract class AbstractNotificationHandler implements NotificationHandler {
    private PayNotification notification;
    private PaymentEntity paymentEntity;

    @Resource
    private PaymentRepository paymentRepository;
    @Resource
    private DefaultTradeNotifier tradeNotifier;

    @Override
    public int handle(PayNotification notification) {
        this.notification = notification;

        if (!isSuccess()) {
            return NotifyReturnEnum.USELESS.getCode();
        }

        if (!initPayment()) {
            return NotifyReturnEnum.FAIL.getCode();
        }

        if (!savePayment()) {
            return NotifyReturnEnum.FAIL.getCode();
        }

        if (notifyTrade() <= 0) {
            return NotifyReturnEnum.FAIL.getCode();
        }

        return NotifyReturnEnum.SUCCESS.getCode();
    }

    protected boolean savePayment() {
        if (paymentEntity.isPaid()) {
            return true;
        }

        paymentEntity.handleSuccessNotification(notification);
        return paymentRepository.save(paymentEntity) > 0;
    }

    protected int notifyTrade() {
        return tradeNotifier.notify(paymentEntity);
    }

    protected boolean initPayment() {
        Payment payment = paymentRepository.find(notification.getPaymentNo());
        if (payment == null) {
            return false;
        }

        paymentEntity = new PaymentEntity(payment);
        return true;
    }


}
