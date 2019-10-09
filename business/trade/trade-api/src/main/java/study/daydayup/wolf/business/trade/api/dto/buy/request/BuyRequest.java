package study.daydayup.wolf.business.trade.api.dto.buy.request;

import lombok.Data;
import study.daydayup.wolf.business.trade.api.dto.buy.TradeMessageDTO;
import study.daydayup.wolf.business.trade.api.model.buy.Buyer;
import study.daydayup.wolf.business.trade.api.dto.buy.TradeGoodsDTO;
import study.daydayup.wolf.business.trade.api.vo.OrderAddress;
import study.daydayup.wolf.business.trade.api.vo.SelfFetchAddress;

/**
 * study.daydayup.wolf.business.trade.api.dto.buy
 *
 * @author Wingle
 * @since 2019/10/5 11:26 AM
 **/
@Data
public class BuyRequest {
    private String orderNo;
    private Buyer buyer;
    private TradeGoodsDTO[] goodsList;
    private OrderAddress orderAddress;
    private SelfFetchAddress selfFetchAddress;
    private TradeMessageDTO tradeMessageDTO;
}
