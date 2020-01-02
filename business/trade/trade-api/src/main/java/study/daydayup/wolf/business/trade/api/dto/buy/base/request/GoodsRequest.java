package study.daydayup.wolf.business.trade.api.dto.buy.base.request;

import lombok.Data;
import study.daydayup.wolf.business.trade.api.domain.vo.buy.GoodsMemo;

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
    private Long  orgId;
    @Min(1)
    private Long  goodsId;
    private Long  skuId;

    private Integer quantity;

    private Integer giftFlag;
    private Long  promotionId;

    private GoodsMemo memo;
}
