package study.daydayup.wolf.business.pay.api.dto.base.pay;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import study.daydayup.wolf.framework.layer.api.Request;

import javax.validation.constraints.DecimalMin;
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
    private boolean doublePayCheck;

    @NonNull @Min(1)
    private Long payerId;
    private String payerName;
    @NonNull @Min(1)
    private Long payeeId;
    private String payeeName;

    @NonNull @DecimalMin("0.01")
    private BigDecimal amount;
    @NonNull
    private Integer currency;

    private Long goodsId;
    private String goodsName;
    private String goodsDesc;

    private String tags;
}
