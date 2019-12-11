package study.daydayup.wolf.business.trade.api.dto.buy;

import lombok.Data;

/**
 * study.daydayup.wolf.business.trade.api.dto.buy
 *
 * @author Wingle
 * @since 2019/10/5 1:28 PM
 **/
@Data
public class TradeGoodsDTO {
    private long orgId;
    private long goodsId;
    private long skuId;

    private int amount;
    private int chargeUnit;

    private long promotionId;

    private GoodsMessageDTO goodsMessageDTO;
}
