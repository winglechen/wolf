package study.daydayup.wolf.business.trade.api.dto.buy.loan;

import lombok.Data;
import study.daydayup.wolf.business.trade.api.domain.enums.TradeTypeEnum;
import study.daydayup.wolf.business.trade.api.domain.vo.BuyerMemo;
import study.daydayup.wolf.business.trade.api.domain.vo.OrderAddress;
import study.daydayup.wolf.business.trade.api.domain.vo.buy.Buyer;
import study.daydayup.wolf.business.trade.api.domain.vo.buy.TradeEnv;
import study.daydayup.wolf.business.trade.api.dto.buy.base.request.GoodsRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.base.request.UmpRequest;
import study.daydayup.wolf.framework.layer.api.Request;

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
public class LoanRequest implements Request {
    @NotNull @Min(1)
    private Long goodsId;

    private String tradeNo;
}
