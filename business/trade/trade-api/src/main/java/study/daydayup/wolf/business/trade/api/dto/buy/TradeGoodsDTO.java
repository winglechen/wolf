package study.daydayup.wolf.business.trade.api.dto.buy;

/**
 * study.daydayup.wolf.business.trade.api.dto.buy
 *
 * @author Wingle
 * @since 2019/10/5 1:28 PM
 **/
public class TradeGoodsDTO {
    private long shopId;
    private long goodsId;
    private long skuId;

    private int amount;
    private int chargeUnit;

    private long promotionId;

    private GoodsMessageDTO goodsMessageDTO;
}
