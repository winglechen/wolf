package study.daydayup.wolf.business.pay.biz.epi;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.pay.api.dto.base.TradeNotifyRequest;

/**
 * study.daydayup.wolf.business.pay.biz.epi
 *
 * @author Wingle
 * @since 2020/3/1 12:31 上午
 **/
@Component
public class DefaultTradeNotifier implements TradeNotifier {
    @Override
    public void notify(TradeNotifyRequest request) {

    }
}
