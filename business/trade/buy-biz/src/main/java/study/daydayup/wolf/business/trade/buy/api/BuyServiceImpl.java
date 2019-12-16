package study.daydayup.wolf.business.trade.buy.api;

import study.daydayup.wolf.business.trade.api.dto.buy.request.PayNotifyRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.request.PayRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.request.BuyRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.response.ConfirmResponse;
import study.daydayup.wolf.business.trade.api.dto.buy.response.PayNotifyResponse;
import study.daydayup.wolf.business.trade.api.dto.buy.response.PayResponse;
import study.daydayup.wolf.business.trade.api.dto.buy.response.PreviewResponse;
import study.daydayup.wolf.business.trade.api.service.buy.BuyService;
import study.daydayup.wolf.business.trade.buy.domain.service.TradeFlowDomainService;

/**
 * study.daydayup.wolf.business.trade.buy.service.impl
 *
 * @author Wingle
 * @since 2019/10/9 1:59 下午
 **/
public class BuyServiceImpl implements BuyService {
    @Override
    public PreviewResponse preview(BuyRequest request) {
        return new TradeFlowDomainService().preview(request);
    }

    @Override
    public ConfirmResponse confirm(BuyRequest request) {
        return new TradeFlowDomainService().confirm(request);
    }

    @Override
    public PayResponse pay(PayRequest request) {
        return new TradeFlowDomainService().pay(request);
    }

    @Override
    public PayNotifyResponse payNotify(PayNotifyRequest request) {
        return new TradeFlowDomainService().payNotify(request);
    }
}
