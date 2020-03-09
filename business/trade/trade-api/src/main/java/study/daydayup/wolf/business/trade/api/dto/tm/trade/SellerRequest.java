package study.daydayup.wolf.business.trade.api.dto.tm.trade;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import study.daydayup.wolf.business.trade.api.dto.order.ContractOption;
import study.daydayup.wolf.business.trade.api.dto.order.OrderOption;
import study.daydayup.wolf.framework.layer.api.Request;

import javax.validation.constraints.Min;

/**
 * study.daydayup.wolf.business.trade.api.dto.tm
 *
 * @author Wingle
 * @since 2020/1/14 10:55 上午
 **/
@Data
@NoArgsConstructor
public class SellerRequest implements Request {
    @NonNull @Min(1)
    private Long sellerId;

    private ContractOption contractOption;
    private OrderOption orderOption;
}
