package study.daydayup.wolf.business.trade.buy.biz.base.node;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.api.service.order.OrderService;
import study.daydayup.wolf.business.trade.buy.biz.base.TradeNode;
import study.daydayup.wolf.business.trade.buy.biz.base.context.BuyContext;


/**
 * study.daydayup.wolf.business.trade.buy.biz.loan.node
 *
 * @author Wingle
 * @since 2019/12/17 8:19 下午
 **/
@Component
public class OrderStoreNode extends AbstractTradeNode implements TradeNode {
    @Reference
    private OrderService orderService;

    @Override
    public void run(BuyContext context) {
        if (context.getRequest().isPreview()) {
            return;
        }

        if (null == context.getOrder()) {
            return;
        }

        orderService.create(context.getOrder());

    }

}
