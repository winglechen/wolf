package study.daydayup.wolf.business.trade.api.dto.buy.base.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@Builder
public class PayResponse implements Response {
    @NonNull
    private String tradeNo;
    private BigDecimal amount;

    private String paymentNo;
    private Integer paymentMethod;

    private Map<String, Object> payArgs;
}
