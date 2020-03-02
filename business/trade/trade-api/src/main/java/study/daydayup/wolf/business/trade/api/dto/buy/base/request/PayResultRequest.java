package study.daydayup.wolf.business.trade.api.dto.buy.base.request;

import lombok.Data;
import lombok.NonNull;
import study.daydayup.wolf.framework.layer.api.Request;

import javax.validation.constraints.Min;
import java.math.BigDecimal;

/**
 * study.daydayup.wolf.business.trade.api.dto.buy.base.request
 *
 * @author Wingle
 * @since 2019/10/9 2:07 下午
 **/
@Data
public class PayResultRequest implements Request {
    @NonNull
    private String tradeNo;
    @NonNull @Min(1)
    private Long buyerId;
    private BigDecimal amount;
}
