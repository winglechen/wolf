package study.daydayup.wolf.business.trade.api.dto.buy.request;

import lombok.Data;
import study.daydayup.wolf.business.trade.api.vo.buy.GoodsMemo;

/**
 * study.daydayup.wolf.business.trade.api.dto.buy
 *
 * @author Wingle
 * @since 2019/10/5 1:28 PM
 **/
@Data
public class GoodsRequest {
    private long orgId;
    private long goodsId;
    private long skuId;

    private int amount;

    private long promotionId;

    private GoodsMemo memo;
}
