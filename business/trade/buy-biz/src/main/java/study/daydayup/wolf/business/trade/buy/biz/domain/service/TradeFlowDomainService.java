package study.daydayup.wolf.business.trade.buy.biz.domain.service;

import study.daydayup.wolf.business.trade.api.dto.buy.request.PayNotifyRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.request.PayRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.request.BuyRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.response.ConfirmResponse;
import study.daydayup.wolf.business.trade.api.dto.buy.response.PayNotifyResponse;
import study.daydayup.wolf.business.trade.api.dto.buy.response.PayResponse;
import study.daydayup.wolf.business.trade.api.dto.buy.response.PreviewResponse;
import study.daydayup.wolf.business.trade.api.enums.TradeTypeEnum;
import study.daydayup.wolf.business.trade.api.service.buy.BuyService;
import study.daydayup.wolf.business.trade.buy.biz.domain.factory.TradeFlowFactory;
import study.daydayup.wolf.business.trade.buy.biz.domain.entity.flow.TradeFlow;
import study.daydayup.wolf.common.util.EnumUtil;

/**
 * study.daydayup.wolf.business.trade.buy.domain.service.impl
 *
 * @author Wingle
 * @since 2019/10/9 2:30 下午
 **/
public class TradeFlowDomainService implements BuyService {
    @Override
    public PreviewResponse preview(BuyRequest request) {
        TradeTypeEnum tradeType = EnumUtil.codeOf(request.getTradeType(), TradeTypeEnum.class);
        TradeFlow tradeFlow = TradeFlowFactory.create(tradeType);

        return tradeFlow.preview(request);
    }

    @Override
    public ConfirmResponse confirm(BuyRequest request) {
        TradeTypeEnum tradeType = EnumUtil.codeOf(request.getTradeType(), TradeTypeEnum.class);
        TradeFlow tradeFlow = TradeFlowFactory.create(tradeType);
        tradeFlow.buildConfirmFlow();

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
