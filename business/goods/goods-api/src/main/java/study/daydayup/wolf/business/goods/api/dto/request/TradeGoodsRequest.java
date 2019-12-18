package study.daydayup.wolf.business.goods.api.dto.request;

import lombok.Data;
import study.daydayup.wolf.framework.layer.api.Request;

/**
 * study.daydayup.wolf.business.goods.api.dto.request
 *
 * @author Wingle
 * @since 2019/12/18 10:01 上午
 **/
@Data
public class TradeGoodsRequest extends Request {
    private long orgId;
    private long goodsId;
    private long skuId;

    private int quantity;
}
