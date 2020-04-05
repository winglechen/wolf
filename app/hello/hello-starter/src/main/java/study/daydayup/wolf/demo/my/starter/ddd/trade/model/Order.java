package study.daydayup.wolf.demo.my.starter.ddd.trade.model;

import lombok.Data;
import study.daydayup.wolf.common.model.annotation.column.BusinessKey;
import study.daydayup.wolf.framework.layer.api.Model;

import java.time.LocalDateTime;
import java.util.List;

/**
 * study.daydayup.wolf.demo.my.starter.ddd.trade
 *
 * @author Wingle
 * @since 2019/12/25 4:15 下午
 **/
@Data
public class Order implements Model {
    @BusinessKey
    private String orderNo;

    private long sellerId;
    private long buyerId;

    private long amount;
    private int state;

    private OrderAddress address;
    private List<OrderLine> orderLines;

    private int version;
    private LocalDateTime createAt;

}
