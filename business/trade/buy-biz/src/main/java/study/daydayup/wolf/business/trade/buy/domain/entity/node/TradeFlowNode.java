package study.daydayup.wolf.business.trade.buy.domain.entity.node;

import study.daydayup.wolf.business.trade.api.dto.buy.request.ConfirmRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.response.ConfirmResponse;
import study.daydayup.wolf.business.trade.buy.domain.entity.context.TradeFlowContext;

/**
 * study.daydayup.wolf.business.trade.buy.domain.entity.node
 *
 * @author Wingle
 * @since 2019/10/5 10:58 AM
 **/
public interface TradeFlowNode {
    void run(ConfirmRequest request, ConfirmResponse response, TradeFlowContext context);
}
