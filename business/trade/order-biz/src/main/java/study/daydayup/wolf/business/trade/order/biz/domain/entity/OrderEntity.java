package study.daydayup.wolf.business.trade.order.biz.domain.entity;

import lombok.Data;
import study.daydayup.wolf.business.trade.api.domain.entity.Order;
import study.daydayup.wolf.framework.layer.domain.Entity;

/**
 * study.daydayup.wolf.business.trade.order.domain.entity
 *
 * @author Wingle
 * @since 2019/10/5 7:21 PM
 **/
@Data
public class OrderEntity implements Entity  {
    private Order model;
    private Order changes;
}
