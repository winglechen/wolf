package study.daydayup.wolf.business.trade.buy.biz.converter;

import study.daydayup.wolf.business.trade.api.domain.entity.Order;
import study.daydayup.wolf.business.trade.api.dto.buy.base.response.PayResponse;
import study.daydayup.wolf.framework.layer.converter.Converter;

/**
 * study.daydayup.wolf.business.trade.order.biz.converter
 *
 * @author Wingle
 * @since 2020/3/5 4:04 下午
 **/
public class OrderConverter implements Converter {
    public static PayResponse toPayResponse(Order order) {
        if (order == null) {
            return null;
        }

        return PayResponse.builder()
                .tradeNo(order.getTradeNo())
                .amount(order.getAmount())
                .build();
    }
}
