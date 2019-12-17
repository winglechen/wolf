package study.daydayup.wolf.business.trade.api.vo.buy;

import lombok.Data;
import study.daydayup.wolf.framework.layer.domain.VO;

/**
 * study.daydayup.wolf.business.trade.api.vo.buy
 *
 * @author Wingle
 * @since 2019/12/13 3:24 下午
 **/
@Data
public class TradeGoods extends VO {
    private long shopId;
    private long goodsId;
    private String goodsName;
    private TradeSku sku;
    private int num;

    private long originalPrice;
    private long discountPrice;
    private long postage;
}
