package study.daydayup.wolf.business.pay.biz.epi;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.pay.biz.domain.entity.PaymentEntity;
import study.daydayup.wolf.business.trade.api.dto.buy.base.TradeNotification;
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
    public int notify(PaymentEntity paymentEntity) {
        TradeNotification notification;


        return 1;
    }
}
