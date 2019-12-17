package study.daydayup.wolf.business.trade.buy.biz.common.flow;


import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.buy.biz.common.AbstractTradeFlow;
import study.daydayup.wolf.business.trade.buy.biz.common.TradeFlow;

/**
 * study.daydayup.wolf.business.trade.buy.domain.entity.flow
 *
 * @author Wingle
 * @since 2019/10/5 10:57 AM
 **/
@Component
public class PeerpayFlow extends AbstractTradeFlow implements TradeFlow {
    @Override
    public void buildConfirmFlow() {

    }

}
