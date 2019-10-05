package study.daydayup.wolf.business.trade.buy.domain.entity;

/**
 * study.daydayup.wolf.business.trade.buy.domain.entity
 *
 * @author Wingle
 * @since 2019/10/5 1:42 PM
 **/
public class TradeGoods {
    private long shopId;
    private long goodsId;
    private String goodsName;
    private TradeSku sku;
    private int num;

    private long originalPrice;
    private long discountPrice;
    private long postage;
}
