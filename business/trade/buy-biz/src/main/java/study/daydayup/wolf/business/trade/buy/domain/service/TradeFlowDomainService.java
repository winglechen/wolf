package study.daydayup.wolf.business.trade.buy.domain.service;

import study.daydayup.wolf.business.trade.api.dto.buy.request.ConfirmRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.request.PayNotifyRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.request.PayRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.request.PrepareRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.response.ConfirmResponse;
import study.daydayup.wolf.business.trade.api.dto.buy.response.PayNotifyResponse;
import study.daydayup.wolf.business.trade.api.dto.buy.response.PayResponse;
import study.daydayup.wolf.business.trade.api.dto.buy.response.PrepareResponse;
import study.daydayup.wolf.business.trade.api.enums.TradeTypeEnum;
import study.daydayup.wolf.business.trade.api.service.buy.BuyService;
import study.daydayup.wolf.business.trade.buy.domain.entity.flow.TradeFlow;
import study.daydayup.wolf.business.trade.buy.domain.factory.TradeFlowFactory;
import study.daydayup.wolf.common.util.EnumUtil;

/**
 * study.daydayup.wolf.business.trade.buy.domain.service.impl
 *
 * @author Wingle
 * @since 2019/10/9 2:30 下午
 **/
public class TradeFlowDomainService implements BuyService {
    @Override
    public PrepareResponse prepare(PrepareRequest request) {
        TradeTypeEnum tradeType = EnumUtil.codeOf(request.getTradeType(), TradeTypeEnum.class);
        TradeFlow tradeFlow = TradeFlowFactory.create(tradeType);

        return tradeFlow.prepare(request);
    }

    @Override
    public ConfirmResponse confirm(ConfirmRequest request) {
        TradeTypeEnum tradeType = EnumUtil.codeOf(request.getTradeType(), TradeTypeEnum.class);
        TradeFlow tradeFlow = TradeFlowFactory.create(tradeType);

        return tradeFlow.confirm(request);
    }

    @Override
    public PayResponse pay(PayRequest request) {
        return null;
    }

    @Override
    public PayNotifyResponse payNotify(PayNotifyRequest request) {
        return null;
    }

}
