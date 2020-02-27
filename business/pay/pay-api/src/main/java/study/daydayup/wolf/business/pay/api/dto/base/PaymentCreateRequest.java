package study.daydayup.wolf.business.pay.api.dto.base;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import study.daydayup.wolf.framework.layer.api.Request;

import javax.validation.constraints.Min;
import java.math.BigDecimal;

/**
 * study.daydayup.wolf.business.pay.api.dto
 *
 * @author Wingle
 * @since 2020/2/27 3:53 下午
 **/
@Data
@Builder
public class PaymentCreateRequest implements Request {
    private Integer paymentMethod;

    @NonNull
    private String tradeNo;
    @NonNull @Min(1)
    private Long payerId;
    private String payerName;
    @NonNull @Min(1)
    private Long payeeId;
    private String payeeName;

    @NonNull
    private BigDecimal amount;
    @NonNull
    private Integer currency;

    private Long goodsId;
    private Long goodsName;
    private Long goodsDesc;

    private String tags;
}
