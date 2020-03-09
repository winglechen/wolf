package study.daydayup.wolf.business.trade.buy.biz.base.context;

import lombok.Data;
import lombok.EqualsAndHashCode;
import study.daydayup.wolf.business.trade.api.domain.entity.Contract;
import study.daydayup.wolf.business.trade.api.domain.entity.Order;
import study.daydayup.wolf.business.trade.api.domain.enums.TradeTypeEnum;
import study.daydayup.wolf.business.trade.api.domain.event.TradeEvent;
import study.daydayup.wolf.business.trade.api.domain.vo.buy.Buyer;
import study.daydayup.wolf.business.trade.api.domain.vo.buy.Seller;
import study.daydayup.wolf.business.trade.api.domain.vo.buy.TradeEnv;
import study.daydayup.wolf.business.trade.api.dto.buy.base.TradeNotification;
import study.daydayup.wolf.business.trade.api.dto.buy.base.request.BuyRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.base.request.PayRequest;
import study.daydayup.wolf.common.lang.ds.ObjectMap;
import study.daydayup.wolf.framework.layer.context.Context;

/**
 * study.daydayup.wolf.business.trade.buy.biz.base.context
 *
 * @author Wingle
 * @since 2020/3/6 1:45 下午
 **/
@EqualsAndHashCode(callSuper = false)
@Data
public class TradeContext extends ObjectMap implements Context {
    private TradeTypeEnum tradeType;

    private Buyer buyer;
    private Seller seller;

    private Contract contract;
    private Order order;

    private String source;
    private TradeEnv env;

    private TradeEvent event;

    private BuyRequest buyRequest;
    private PayRequest payRequest;
    private TradeNotification notification;

}
