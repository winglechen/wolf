package study.daydayup.wolf.business.trade.buy.domain.entity.flow;

import study.daydayup.wolf.business.trade.api.dto.buy.request.ConfirmRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.response.ConfirmResponse;
import study.daydayup.wolf.business.trade.api.service.buy.BuyService;
import study.daydayup.wolf.business.trade.buy.domain.entity.node.TradeFlowNode;

/**
 * study.daydayup.wolf.business.trade.buy.domain.entity.flow
 *
 * @author Wingle
 * @since 2019/10/5 10:54 AM
 **/
public interface TradeFlow extends BuyService {
    void init();
    void addNode(TradeFlowNode node);
    void buildConfirmFlow();
}
