package study.daydayup.wolf.business.trade.api.dto.buy.loan;

import lombok.Data;
import lombok.NonNull;
import study.daydayup.wolf.business.trade.api.dto.TradeId;
import study.daydayup.wolf.framework.layer.api.Request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * study.daydayup.wolf.business.trade.api.dto.buy.base.request
 *
 * @author Wingle
 * @since 2019/10/9 1:43 下午
 **/
@Data
public class RepayRequest implements Request {

    @NonNull
    private String tradeNo;
    private Integer installmentNo;
    private Integer payMethod;

}
