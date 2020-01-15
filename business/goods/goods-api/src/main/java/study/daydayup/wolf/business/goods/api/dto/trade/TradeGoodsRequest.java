package study.daydayup.wolf.business.goods.api.dto.trade;

import lombok.Builder;
import lombok.Data;
import study.daydayup.wolf.framework.layer.api.Request;

/**
 * study.daydayup.wolf.business.goods.api.dto.request
 *
 * @author Wingle
 * @since 2019/12/18 10:01 上午
 **/
@Data
@Builder
public class TradeGoodsRequest implements Request {
    private Long orgId;
    private Long goodsId;
    private Long skuId;

    private Integer quantity;
}
