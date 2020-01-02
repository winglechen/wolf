package study.daydayup.wolf.business.trade.buy.biz.base.node;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.buy.biz.base.TradeNode;
import study.daydayup.wolf.business.trade.buy.biz.base.context.BuyContext;

/**
 * study.daydayup.wolf.business.trade.buy.domain.entity.node
 *
 * @author Wingle
 * @since 2019/10/5 12:10 PM
 **/
@Component
public class ContractCreateNode extends AbstractTradeNode implements TradeNode {
    @Override
    public void run(BuyContext context) {
        init(context);


    }

}
