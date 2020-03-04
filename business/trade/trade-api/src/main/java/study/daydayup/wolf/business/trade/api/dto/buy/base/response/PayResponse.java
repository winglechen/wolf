package study.daydayup.wolf.business.trade.api.dto.buy.base.response;

import lombok.Data;
import lombok.NonNull;
import study.daydayup.wolf.framework.layer.api.Response;

import java.math.BigDecimal;
import java.util.Map;

/**
 * study.daydayup.wolf.business.trade.api.dto.buy.base.response
 *
 * @author Wingle
 * @since 2019/10/9 1:42 下午
 **/
@Data
public class PayResponse implements Response {
    @NonNull
    private String tradeNo;
    private String paymentNo;
    private BigDecimal amount;
    private Map<String, Object> payArgs;
}
