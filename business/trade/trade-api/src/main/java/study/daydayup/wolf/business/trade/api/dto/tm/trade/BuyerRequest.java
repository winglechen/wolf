package study.daydayup.wolf.business.trade.api.dto.tm.trade;

import study.daydayup.wolf.business.trade.api.dto.order.ContractOption;
import study.daydayup.wolf.business.trade.api.dto.order.OrderOption;
import study.daydayup.wolf.framework.layer.api.Request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * study.daydayup.wolf.business.trade.api.dto.tm
 *
 * @author Wingle
 * @since 2020/1/14 10:54 上午
 **/
public class BuyerRequest implements Request {
    @NotNull @Min(1)
    private Long buyerId;

    private ContractOption contractOption;
    private OrderOption orderOption;
}
