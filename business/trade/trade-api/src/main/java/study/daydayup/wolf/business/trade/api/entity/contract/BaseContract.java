package study.daydayup.wolf.business.trade.api.entity.contract;

import lombok.Data;
import study.daydayup.wolf.business.trade.api.enums.TradeTypeEnum;
import study.daydayup.wolf.framework.layer.api.Model;

/**
 * study.daydayup.wolf.business.trade.api.entity.contract
 *
 * @author Wingle
 * @since 2019/12/16 2:42 下午
 **/
@Data
public class BaseContract extends Model {
    private String tradeNo;
    /**
     * @see TradeTypeEnum
     */
    private int tradeType;
    private int state;

    private long buyerId;
    private String buyerName;
    private long sellerId;
    private String sellerName;

    private String source;
    private String tags;

}
