package study.daydayup.wolf.business.trade.buy.biz.base.context;

import lombok.Data;
import study.daydayup.wolf.business.trade.api.domain.entity.Contract;
import study.daydayup.wolf.business.trade.api.domain.entity.Order;
import study.daydayup.wolf.business.trade.api.domain.enums.TradeTypeEnum;
import study.daydayup.wolf.business.trade.api.domain.vo.buy.Buyer;
import study.daydayup.wolf.business.trade.api.domain.vo.buy.Seller;
import study.daydayup.wolf.business.trade.api.domain.vo.buy.TradeEnv;

/**
 * study.daydayup.wolf.business.trade.buy.biz.base.context
 *
 * @author Wingle
 * @since 2020/3/6 1:45 下午
 **/
@Data
public class TradeContext {
    private TradeTypeEnum tradeType;

    private Buyer buyer;
    private Seller seller;

    private Contract contract;
    private Order order;

    private String source;
    private TradeEnv env;

}
