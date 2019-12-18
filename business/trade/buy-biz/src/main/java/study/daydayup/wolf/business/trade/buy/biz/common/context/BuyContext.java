package study.daydayup.wolf.business.trade.buy.biz.common.context;

import lombok.Builder;
import lombok.Data;
import study.daydayup.wolf.business.trade.api.dto.buy.request.BuyRequest;
import study.daydayup.wolf.business.trade.api.entity.Contract;
import study.daydayup.wolf.business.trade.api.entity.Order;
import study.daydayup.wolf.business.trade.api.enums.TradePhaseEnum;
import study.daydayup.wolf.business.trade.api.enums.TradeTypeEnum;
import study.daydayup.wolf.business.trade.api.vo.buy.Buyer;
import study.daydayup.wolf.business.trade.api.vo.buy.Seller;
import study.daydayup.wolf.business.trade.api.vo.buy.TradeGoods;
import study.daydayup.wolf.business.trade.api.vo.buy.TradeUmp;

import java.util.List;

/**
 * study.daydayup.wolf.business.trade.buy.domain.entity
 *
 * @author Wingle
 * @since 2019/10/5 11:01 AM
 **/
@Data
public class BuyContext {
    @Builder
    BuyContext(){}

    private TradeTypeEnum tradeType;
    private TradePhaseEnum tradePhase;

    private BuyRequest request;
    private Buyer buyer;
    private Seller seller;

    private List<TradeGoods> goods;
    private TradeUmp ump;

    private Contract contract;
    private Order order;
}
