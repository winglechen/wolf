package study.daydayup.wolf.business.trade.buy.biz.virture.flow;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.buy.biz.base.AbstractTradeFlow;
import study.daydayup.wolf.business.trade.buy.biz.base.TradeFlow;
import study.daydayup.wolf.business.trade.buy.biz.base.TradeNode;
import study.daydayup.wolf.business.trade.buy.biz.base.node.OrderCreateNode;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * study.daydayup.wolf.business.trade.buy.loan
 *
 * @author Wingle
 * @since 2019/12/13 4:42 下午
 **/
@Component
public class AuditFlow extends AbstractTradeFlow implements TradeFlow {
    @Resource
    private OrderCreateNode orderCreateNode;

    @Override
    public List<TradeNode> buildConfirmFlow() {
        return null;
    }

    @Override
    public List<TradeNode> buildPreviewFlow() {
        List<TradeNode> nodeList = new ArrayList<>();

        nodeList.add(orderCreateNode);

        return nodeList;
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
