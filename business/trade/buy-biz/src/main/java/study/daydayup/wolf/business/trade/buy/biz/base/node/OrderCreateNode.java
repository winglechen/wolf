package study.daydayup.wolf.business.trade.buy.biz.base.node;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.api.domain.entity.Order;
import study.daydayup.wolf.business.trade.api.domain.state.TradeState;
import study.daydayup.wolf.business.trade.api.domain.state.base.WaitToPayState;
import study.daydayup.wolf.business.trade.api.domain.state.loan.contract.WaitToApproveState;
import study.daydayup.wolf.business.trade.api.domain.vo.buy.Buyer;
import study.daydayup.wolf.business.trade.api.domain.vo.buy.Goods;
import study.daydayup.wolf.business.trade.api.domain.vo.buy.Seller;
import study.daydayup.wolf.business.trade.api.dto.buy.base.request.BuyRequest;
import study.daydayup.wolf.business.trade.buy.biz.base.TradeNode;
import study.daydayup.wolf.business.trade.buy.biz.base.context.BuyContext;
import study.daydayup.wolf.common.model.type.id.TradeNo;
import study.daydayup.wolf.common.util.lang.StringUtil;
import study.daydayup.wolf.framework.layer.context.RpcContext;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.trade.buy.domain.entity.node
 *
 * @author Wingle
 * @since 2019/10/5 12:10 PM
 **/
@Component
public class OrderCreateNode extends AbstractTradeNode implements TradeNode {
    protected Order order;

    @Resource
    private RpcContext rpcContext;

    @Override
    public void run(BuyContext context) {
        init(context);

        initOrder();
        initSellerAndBuyer();
        initGoodsInfo();

        context.setOrder(order);
    }

    protected void initGoodsInfo() {
        if (null == context.getRequest().getGoods()) {
            handleMultiGoods();
            return;
        }

        handleSingleGoods();
    }

    protected void handleSingleGoods() {
        Goods goods = context.getRequest().getGoods();

        order.setCurrency(goods.getCurrency());
        order.setAmount(goods.getPayPrice());
        order.setPostage(goods.getPostage());
    }

    protected void handleMultiGoods() {

    }

    protected void initTradeState() {
        TradeState requestTradeState = context.getRequest().getTradeState();
        if (requestTradeState != null) {
            order.setState(requestTradeState);
        } else {
            order.setState(new WaitToPayState());
        }
    }

    protected void initRelatedTradeNo() {
        String relatedTradeNo = context.getRequest().getRelatedTradeNo();
        if (StringUtil.isBlank(relatedTradeNo)) {
            return;
        }

        order.setRelatedTradeNo(relatedTradeNo);
    }

    protected void initOrder() {
        order = Order.builder()
                .tradeType(context.getTradeType().getCode())
                .source(context.getRequest().getSource())
                .createdAt(rpcContext.getRequestTime())
                .build();

        createTradeNo();
        initTradeState();
        initRelatedTradeNo();
    }

    protected void initSellerAndBuyer() {
        Buyer buyer = context.getRequest().getBuyer();
        Seller seller = context.getRequest().getSeller();

        order.setBuyerId(buyer.getId());
        order.setBuyerName(buyer.getName());
        order.setSellerId(seller.getId());
        order.setSellerName(seller.getName());
    }

    private void createTradeNo() {
        BuyRequest request = context.getRequest();
        if (null != request.getTradeNo()) {
            order.setTradeNo(request.getTradeNo());
            return;
        }

        String tradeNo = TradeNo.builder()
                .tradePhase(context.getTradePhase())
                .accountId(context.getBuyer().getId())
                .build()
                .create();
        order.setTradeNo(tradeNo);
    }


}
