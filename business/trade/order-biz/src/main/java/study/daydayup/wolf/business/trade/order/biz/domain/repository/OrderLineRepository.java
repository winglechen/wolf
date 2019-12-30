package study.daydayup.wolf.business.trade.order.biz.domain.repository;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.api.dto.TradeId;
import study.daydayup.wolf.business.trade.api.entity.Order;
import study.daydayup.wolf.business.trade.api.entity.order.OrderLine;
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
public class OrderLineRepository extends AbstractRepository implements Repository {
    public void add(List<OrderLine> orderLine) {

    }

    public void save(List<OrderLine> key, List<OrderLine> changes) {

    }

    public List<OrderLine> find(TradeId tradeId) {
        return null;
    }
}
