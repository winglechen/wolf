package study.daydayup.wolf.business.pay.biz.epi;

import study.daydayup.wolf.business.pay.api.dto.base.TradeNotifyRequest;

/**
 * study.daydayup.wolf.business.pay.biz.epi
 *
 * @author Wingle
 * @since 2020/3/1 12:31 上午
 **/
public interface TradeNotifier {
    void notify(TradeNotifyRequest request);
}
