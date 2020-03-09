package study.daydayup.wolf.business.trade.order.biz.domain.repository.buyer;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.api.domain.entity.Contract;
import study.daydayup.wolf.business.trade.api.domain.entity.Order;
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

    public Page<Contract> findAll(@NonNull Long buyerId,  PageRequest pageReq) {
        Page.startPage(pageReq.getPageNum(), pageReq.getPageSize());
        List<OrderDO> orderDOList = orderDAO.selectByBuyerId(buyerId);
        if (CollectionUtil.isEmpty(orderDOList)) {
            return Page.empty(pageReq.getPageNum(), pageReq.getPageSize());
        }


        return null;
    }

    public Order findLatest(@NonNull Long buyerId, Long sellerId) {
        return null;
    }


}
