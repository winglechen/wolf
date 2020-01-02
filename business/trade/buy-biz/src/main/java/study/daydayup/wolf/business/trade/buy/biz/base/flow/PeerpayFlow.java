package study.daydayup.wolf.business.trade.buy.biz.base.flow;


import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.buy.biz.base.AbstractTradeFlow;
import study.daydayup.wolf.business.trade.buy.biz.base.TradeFlow;
import study.daydayup.wolf.business.trade.buy.biz.base.TradeNode;

import java.util.List;

/**
 * study.daydayup.wolf.business.trade.buy.domain.entity.flow
 *
 * @author Wingle
 * @since 2019/10/5 10:57 AM
 **/
@Component
public class PeerpayFlow extends AbstractTradeFlow implements TradeFlow {

    @Override
    public List<TradeNode> buildConfirmFlow() {
        return null;
    }

    @Override
    public List<TradeNode> buildPreviewFlow() {
        return null;
    }

    @Override
    public List<TradeNode> buildPayFlow() {
        return null;
    }

    @Override
    public List<TradeNode> buildPayNotifyFlow() {
        return null;
    }
}
