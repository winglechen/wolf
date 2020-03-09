package study.daydayup.wolf.business.trade.order.biz.domain.repository.seller;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.api.domain.entity.Order;
import study.daydayup.wolf.business.trade.api.dto.TradeOwner;
import study.daydayup.wolf.business.trade.api.dto.tm.contract.seller.BuyerRequest;
import study.daydayup.wolf.business.trade.api.dto.tm.contract.seller.StateRequest;
import study.daydayup.wolf.business.trade.api.dto.tm.contract.seller.TypeRequest;
import study.daydayup.wolf.business.trade.api.dto.tm.trade.TradeIds;
import study.daydayup.wolf.business.trade.order.biz.dal.dao.OrderDAO;
import study.daydayup.wolf.business.trade.order.biz.dal.dataobject.OrderDO;
import study.daydayup.wolf.business.trade.order.biz.domain.repository.OrderQueryRepository;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.common.util.collection.ListUtil;
import study.daydayup.wolf.framework.rpc.page.Page;
import study.daydayup.wolf.framework.rpc.page.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * study.daydayup.wolf.business.trade.order.biz.domain.repository.seller
 *
 * @author Wingle
 * @since 2020/1/18 10:35 下午
 **/
@Component
public class SellerOrderRepository extends OrderQueryRepository {
    @Resource
    private OrderDAO orderDAO;

    public List<Order> findByTradeNos(TradeIds tradeIds) {
        tradeIds.valid();
        List<OrderDO> orderDOList = orderDAO.selectByTradeNoIn(tradeIds.getTradeNoSet(), tradeIds.getBuyerId(), tradeIds.getSellerId());
        if (orderDOList == null || orderDOList.isEmpty()) {
            return ListUtil.empty();
        }

        TradeOwner owner = new TradeOwner();
        owner.setSellerId(tradeIds.getSellerId());
        owner.setBuyerId(tradeIds.getBuyerId());

        return findExtraByOrderList(orderDOList, owner);
    }

    public Page<Order> findAll(@NonNull Long sellerId, PageRequest pageReq) {
        Page.startPage(pageReq.getPageNum(), pageReq.getPageSize());
        List<OrderDO> orderDOList = orderDAO.selectBySellerId(sellerId);
        if (orderDOList == null || orderDOList.isEmpty()) {
            return Page.empty(pageReq.getPageNum(), pageReq.getPageSize());
        }

        TradeOwner owner = new TradeOwner();
        owner.setSellerId(sellerId);

        List<Order> orderList = findExtraByOrderList(orderDOList, owner);
        return Page.of(orderDOList).to(orderList);
    }

    public List<Order> findByRelatedTradeNo(@NonNull String relatedTradeNo, @NonNull Long sellerId) {
        List<OrderDO> orderDOList = orderDAO.selectByRelatedTradeNo(relatedTradeNo, sellerId);
        if (CollectionUtil.isEmpty(orderDOList)) {
            return ListUtil.empty();
        }

        TradeOwner owner = new TradeOwner();
        owner.setSellerId(sellerId);

        return findExtraByOrderList(orderDOList, owner);
    }

    public Page<Order> findByTradeType(TypeRequest request, PageRequest pageReq) {
        Page.startPage(pageReq.getPageNum(), pageReq.getPageSize());
        List<OrderDO> orderDOList = orderDAO.sellerByTradeType(request);
        if (orderDOList == null || orderDOList.isEmpty()) {
            return Page.empty(pageReq.getPageNum(), pageReq.getPageSize());
        }
        TradeOwner owner = new TradeOwner();
        owner.setSellerId(request.getSellerId());

        List<Order> orderList = findExtraByOrderList(orderDOList, owner);
        return Page.of(orderDOList).to(orderList);
    }

    public Page<Order> findByTradeState(StateRequest request, PageRequest pageReq) {
        Page.startPage(pageReq.getPageNum(), pageReq.getPageSize());
        List<OrderDO> orderDOList = orderDAO.sellerByState(request);
        if (orderDOList == null || orderDOList.isEmpty()) {
            return Page.empty(pageReq.getPageNum(), pageReq.getPageSize());
        }
        TradeOwner owner = new TradeOwner();
        owner.setSellerId(request.getSellerId());

        List<Order> orderList = findExtraByOrderList(orderDOList, owner);
        return Page.of(orderDOList).to(orderList);
    }

    public Page<Order> findByBuyerId(BuyerRequest request, PageRequest pageReq) {
        Page.startPage(pageReq.getPageNum(), pageReq.getPageSize());
        List<OrderDO> orderDOList = orderDAO.sellerByBuyerId(request);
        if (orderDOList == null || orderDOList.isEmpty()) {
            return Page.empty(pageReq.getPageNum(), pageReq.getPageSize());
        }
        TradeOwner owner = new TradeOwner();
        owner.setSellerId(request.getSellerId());

        List<Order> orderList = findExtraByOrderList(orderDOList, owner);
        return Page.of(orderDOList).to(orderList);
    }

}
