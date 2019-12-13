package study.daydayup.wolf.business.trade.api.dto.buy.request;

import lombok.Data;
import study.daydayup.wolf.business.trade.api.vo.BuyerMemo;
import study.daydayup.wolf.business.trade.api.vo.buy.Buyer;
import study.daydayup.wolf.business.trade.api.vo.TradeAddress;
import study.daydayup.wolf.business.trade.api.vo.buy.TradeEnv;
import study.daydayup.wolf.framework.layer.api.Request;

import java.util.List;

/**
 * study.daydayup.wolf.business.trade.api.dto.buy.request
 *
 * @author Wingle
 * @since 2019/10/9 1:43 下午
 **/
@Data
public class PreviewRequest extends Request {
    private Buyer buyer;

    /**
     * @see study.daydayup.wolf.business.trade.api.enums.TradeTypeEnum
     */
    private int tradeType;
    private String relatedTradeNo;

    private String tradeSource;
    private TradeEnv env;
    private String tags;

    private TradeAddress address;
    private BuyerMemo buyerMemo;

    private UmpRequest umpRequest;
    private List<GoodsRequest> goodsRequestList;
}
