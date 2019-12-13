package study.daydayup.wolf.business.trade.api.dto.buy.request;

import lombok.Data;
import study.daydayup.wolf.business.trade.api.vo.BuyerMemo;
import study.daydayup.wolf.business.trade.api.vo.buy.Buyer;
import study.daydayup.wolf.business.trade.api.vo.TradeAddress;
import study.daydayup.wolf.framework.layer.api.Request;

/**
 * study.daydayup.wolf.business.trade.api.dto.buy.request
 *
 * @author Wingle
 * @since 2019/10/9 1:43 下午
 **/
@Data
public class PreviewRequest extends Request {
    /**
     * @see study.daydayup.wolf.business.trade.api.enums.TradeTypeEnum
     */
    private int tradeType;
    private Buyer buyer;
    private GoodsRequest[] goodsList;
    private TradeAddress orderAddress;
    private BuyerMemo buyerMemo;
}
