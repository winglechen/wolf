package study.daydayup.wolf.business.trade.order.biz.domain.repository.buyer;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.api.domain.entity.Order;
import study.daydayup.wolf.business.trade.api.dto.TradeId;
import study.daydayup.wolf.business.trade.api.dto.TradeOwner;
import study.daydayup.wolf.business.trade.order.biz.dal.dao.OrderDAO;
import study.daydayup.wolf.business.trade.order.biz.dal.dataobject.OrderDO;
import study.daydayup.wolf.business.trade.order.biz.domain.repository.OrderQueryRepository;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.framework.rpc.page.Page;
import study.daydayup.wolf.framework.rpc.page.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * study.daydayup.wolf.business.trade.order.biz.domain.repository.buyer
 *
 * @author Wingle
 * @since 2020/1/17 8:46 下午
 **/
@Component
public class BuyerOrderRepository extends OrderQueryRepository {
    @Resource
    private OrderDAO orderDAO;

    public Page<Order> findAll(@NonNull Long buyerId,  PageRequest pageReq) {
        Page.startPage(pageReq.getPageNum(), pageReq.getPageSize());
        List<OrderDO> orderDOList = orderDAO.selectByBuyerId(buyerId);
        if (CollectionUtil.isEmpty(orderDOList)) {
            return Page.empty(pageReq.getPageNum(), pageReq.getPageSize());
        }

        TradeOwner owner = new TradeOwner();
        owner.setBuyerId(buyerId);

        List<Order> orderList = findExtraByOrderList(orderDOList, owner);
        return Page.of(orderDOList).to(orderList);
    }

    public Order findLatest(@NonNull Long buyerId, Long sellerId) {
        OrderDO orderDO = orderDAO.selectLatestByBuyer(buyerId, sellerId);
        if (orderDO == null) {
            return null;
        }

        TradeId tradeId = new TradeId();
        tradeId.setTradeNo(orderDO.getTradeNo());
        tradeId.setBuyerId(orderDO.getBuyerId());

        return findExtraByOrder(orderDO, tradeId);
    }


}
