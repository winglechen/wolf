package study.daydayup.wolf.business.trade.api.dto.buy.request;

import lombok.Data;
import study.daydayup.wolf.business.trade.api.dto.buy.TradeGoodsDTO;
import study.daydayup.wolf.business.trade.api.dto.buy.TradeMessageDTO;
import study.daydayup.wolf.business.trade.api.entity.buy.Buyer;
import study.daydayup.wolf.business.trade.api.vo.TradeAddress;
import study.daydayup.wolf.business.trade.api.vo.SelfFetchAddress;

/**
 * study.daydayup.wolf.business.trade.api.dto.buy.request
 *
 * @author Wingle
 * @since 2019/10/9 1:43 下午
 **/
@Data
public class PreviewRequest {
    /**
     * @see study.daydayup.wolf.business.trade.api.enums.TradeTypeEnum
     */
    private int tradeType;
    private Buyer buyer;
    private TradeGoodsDTO[] goodsList;
    private TradeAddress orderAddress;
    private SelfFetchAddress selfFetchAddress;
    private TradeMessageDTO tradeMessageDTO;
}
