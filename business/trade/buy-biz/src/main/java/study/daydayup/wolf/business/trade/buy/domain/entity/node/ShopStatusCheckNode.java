package study.daydayup.wolf.business.trade.buy.domain.entity.node;


import study.daydayup.wolf.business.trade.api.dto.buy.request.ConfirmRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.response.ConfirmResponse;
import study.daydayup.wolf.business.trade.buy.domain.entity.context.TradeFlowContext;

/**
 * study.daydayup.wolf.business.trade.buy.domain.entity.node
 *
 * @author Wingle
 * @since 2019/10/5 10:59 AM
 **/
public class ShopStatusCheckNode extends AbstractTradeFlowNode implements TradeFlowNode {
    @Override
    public void run(ConfirmRequest request, ConfirmResponse response, TradeFlowContext context) {

    }
}
