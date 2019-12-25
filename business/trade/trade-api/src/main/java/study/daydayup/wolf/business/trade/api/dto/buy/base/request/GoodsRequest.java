package study.daydayup.wolf.business.trade.api.dto.buy.base.request;

import lombok.Data;
import study.daydayup.wolf.business.trade.api.vo.buy.GoodsMemo;

import javax.validation.constraints.Min;

/**
 * study.daydayup.wolf.business.trade.api.dto.buy
 *
 * @author Wingle
 * @since 2019/10/5 1:28 PM
 **/
@Data
public class GoodsRequest {
    @Min(1)
    private long orgId;
    @Min(1)
    private long goodsId;
    private long skuId;

    private int quantity;

    private int giftFlag;
    private long promotionId;

    private GoodsMemo memo;
}
