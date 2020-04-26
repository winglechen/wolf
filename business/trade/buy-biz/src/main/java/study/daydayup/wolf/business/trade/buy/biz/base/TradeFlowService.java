package study.daydayup.wolf.business.trade.buy.biz.base;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.api.dto.buy.base.request.PayResultRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.base.request.PayRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.base.request.BuyRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.base.response.BuyResponse;
import study.daydayup.wolf.business.trade.api.dto.buy.base.response.PayResultResponse;
import study.daydayup.wolf.business.trade.api.dto.buy.base.response.PayResponse;
import study.daydayup.wolf.business.trade.api.domain.enums.TradeTypeEnum;
import study.daydayup.wolf.common.util.lang.EnumUtil;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.trade.buy.domain.service.impl
 *
 * @author Wingle
 * @since 2019/10/9 2:30 下午
 **/
@Component
public class TradeFlowService {
    @Resource
    private TradeFlowFactory flowFactory;

    public BuyResponse preview(BuyRequest request) {
        TradeTypeEnum tradeType = EnumUtil.codeOf(request.getTradeType(), TradeTypeEnum.class);
        TradeFlow tradeFlow = flowFactory.create(tradeType);

        return tradeFlow.preview(request);
    }

    public BuyResponse confirm(BuyRequest request) {
        TradeTypeEnum tradeType = EnumUtil.codeOf(request.getTradeType(), TradeTypeEnum.class);
        TradeFlow tradeFlow = flowFactory.create(tradeType);
        tradeFlow.buildConfirmFlow();

        return tradeFlow.confirm(request);
    }

    public PayResponse pay(PayRequest request) {
        return null;
    }

    public PayResultResponse payNotify(PayResultRequest request) {
        return null;
    }

}
