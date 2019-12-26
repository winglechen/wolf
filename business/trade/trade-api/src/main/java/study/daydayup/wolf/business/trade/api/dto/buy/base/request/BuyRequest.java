package study.daydayup.wolf.business.trade.api.dto.buy.base.request;

import lombok.Data;
import study.daydayup.wolf.business.trade.api.vo.BuyerMemo;
import study.daydayup.wolf.business.trade.api.vo.buy.Buyer;
import study.daydayup.wolf.business.trade.api.vo.TradeAddress;
import study.daydayup.wolf.business.trade.api.vo.buy.TradeEnv;
import study.daydayup.wolf.framework.layer.api.Request;
import study.daydayup.wolf.business.trade.api.enums.TradeTypeEnum;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * study.daydayup.wolf.business.trade.api.dto.buy.base.request
 *
 * @author Wingle
 * @since 2019/10/9 1:43 下午
 **/
@Data
public class BuyRequest implements Request {
    private String tradeNo;
    @NotNull
    private Buyer buyer;

    /**
     * @see TradeTypeEnum
     */
    @NotNull @Min(1)
    private Integer tradeType;
    private String relatedTradeNo;

    private String source;
    private TradeEnv env;
    private String tags;

    private TradeAddress address;
    private BuyerMemo buyerMemo;

    private UmpRequest umpRequest;
    @NotNull
    private List<GoodsRequest> goodsRequestList;
}
