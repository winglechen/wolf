package study.daydayup.wolf.business.trade.order.biz.domain.repository.order;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.api.dto.TradeId;
import study.daydayup.wolf.business.trade.api.domain.vo.OrderAddress;
import study.daydayup.wolf.business.trade.api.dto.tm.trade.TradeIds;
import study.daydayup.wolf.framework.layer.domain.AbstractRepository;
import study.daydayup.wolf.framework.layer.domain.Repository;

import java.util.List;

/**
 * study.daydayup.wolf.business.trade.order.biz.domain.repository
 *
 * @author Wingle
 * @since 2019/12/26 9:06 下午
 **/
@Component
public class OrderAddressRepository extends AbstractRepository implements Repository {
    public void add(OrderAddress address) {

    }

    public void save(OrderAddress key, OrderAddress changes) {

    }

    public OrderAddress find(TradeId tradeId) {
        return null;
    }

    public List<OrderAddress> find(TradeIds tradeIds) {
        return null;
    }
}
